.class public abstract Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static synthetic $r8$lambda$-1vfWZdCmKAAke5Fas3luu-Aqq4(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 215
    invoke-static {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->checkBlurEnabled(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result p0

    if-nez p0, :cond_1

    if-eqz p2, :cond_0

    .line 217
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefault:I

    goto :goto_0

    :cond_0
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_topPanelBackground:I

    .line 216
    :goto_0
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    const/16 p1, 0xff

    invoke-static {p0, p1}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result p0

    return p0

    :cond_1
    const/high16 p0, 0x40000

    .line 220
    invoke-static {p0}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p0

    if-eqz p0, :cond_2

    const p0, 0x3f59999a    # 0.85f

    goto :goto_1

    :cond_2
    const p0, 0x3f428f5c    # 0.76f

    .line 221
    :goto_1
    sget p2, Lorg/telegram/ui/ActionBar/Theme;->key_chat_topPanelBackground:I

    invoke-static {p2, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p1

    .line 222
    invoke-static {p1, p0}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$95tzLDa89xntyIIe-_kss-csLsA(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 1

    const/high16 p1, 0x40000

    .line 85
    invoke-static {p1}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_0
    const p1, 0x3f428f5c    # 0.76f

    .line 86
    :goto_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    .line 87
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$E2ekdkVA5GhQenCIourmaRxh0b4(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 1

    const/high16 p1, 0x40000

    .line 252
    invoke-static {p1}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_0
    const p1, 0x3f428f5c    # 0.76f

    .line 253
    :goto_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_undo_background:I

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    .line 254
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$E5ak8mLTAF7-d3CTsOGRDBxI0Wo(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 191
    invoke-static {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->checkBlurEnabled(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result p0

    if-nez p0, :cond_1

    if-eqz p2, :cond_0

    .line 193
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefault:I

    goto :goto_0

    :cond_0
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_topPanelBackground:I

    .line 192
    :goto_0
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    const/16 p1, 0xff

    invoke-static {p0, p1}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result p0

    return p0

    .line 196
    :cond_1
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_topPanelBackground:I

    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    const/high16 p1, 0x40000

    .line 197
    invoke-static {p1}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p1

    .line 198
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/ChatHeaderUiHelper;->isMaterial3ChatHeaderStyle()Z

    move-result p2

    if-eqz p2, :cond_3

    if-eqz p1, :cond_2

    const p1, 0x3f6e147b    # 0.93f

    goto :goto_1

    :cond_2
    const p1, 0x3f666666    # 0.9f

    goto :goto_1

    :cond_3
    if-eqz p1, :cond_4

    const p1, 0x3f59999a    # 0.85f

    goto :goto_1

    :cond_4
    const p1, 0x3f428f5c    # 0.76f

    .line 201
    :goto_1
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$Ey0ciPZzjiMsZuFQ-_v8uWwdfo0(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 2

    const/high16 p1, 0x40000

    .line 271
    invoke-static {p1}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_0
    const p1, 0x3f428f5c    # 0.76f

    .line 272
    :goto_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 273
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messagePanelBackground:I

    invoke-static {v1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    .line 274
    invoke-static {v0, p0, p1}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->solveSrcColor(IIF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$Fd_1kgro3whVhcO8eYoVc5pG_Ww(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 205
    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getDividerColor(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$KRa5xYxJoLBG4llc1t3CMeUYNfo(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 135
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultSubmenuBackground:I

    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    if-eqz p1, :cond_0

    const p1, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_0
    const p1, 0x3f533333    # 0.825f

    :goto_0
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$MB_-LqQxeNGauM_z9i_xeGRDLuU(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 161
    const/high16 p0, 0x33000000

    return p0
.end method

.method public static synthetic $r8$lambda$RlNs06gnkX92t5BmifrwF6d2b9I(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 237
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-static {p1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    const p1, 0x3f333333    # 0.7f

    .line 238
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$WRfHXHldZtMx9acDdcF-vqQPS80(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 172
    invoke-static {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->checkBlurEnabled(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result p0

    if-nez p0, :cond_0

    .line 173
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messagePanelBackground:I

    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    const/16 p1, 0xff

    invoke-static {p0, p1}, Landroidx/core/graphics/ColorUtils;->setAlphaComponent(II)I

    move-result p0

    return p0

    :cond_0
    const/high16 p0, 0x40000

    .line 176
    invoke-static {p0}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p0

    if-eqz p0, :cond_1

    const p0, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_1
    const p0, 0x3f428f5c    # 0.76f

    .line 177
    :goto_0
    sget p2, Lorg/telegram/ui/ActionBar/Theme;->key_chat_messagePanelBackground:I

    invoke-static {p2, p1}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p1

    .line 178
    invoke-static {p1, p0}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$aljF0JGFaKv3yL9Uyiq_MTaWaRA(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 1

    const/high16 p1, 0x40000

    .line 60
    invoke-static {p1}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_0
    const p1, 0x3f428f5c    # 0.76f

    .line 61
    :goto_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_dialogBackground:I

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$bh8Wi0pKzsRvX8abf50Bjdo7WvI(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 2

    const/high16 p1, 0x40000

    .line 44
    invoke-static {p1}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_0
    const p1, 0x3f428f5c    # 0.76f

    .line 45
    :goto_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 46
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_glass_targetMainTopPanel:I

    invoke-static {v1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    .line 47
    invoke-static {v0, p0, p1}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->solveSrcColor(IIF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$cVQN6xfrBp8WTGSyjeBQRzVi88g(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 75
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-static {p1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    const p1, 0x3f6147ae    # 0.88f

    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$gjk22_c-7zCYamLTAxT-Wm_snjw(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 1

    const/high16 p1, 0x40000

    .line 147
    invoke-static {p1}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_0
    const p1, 0x3f428f5c    # 0.76f

    .line 148
    :goto_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    .line 149
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$kVTZ-_qz8TMV_AzYRhCiuQLJlNs(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 311
    sget p1, Lorg/telegram/ui/ActionBar/Theme;->key_dialogBackground:I

    invoke-static {p1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    const p1, 0x3f47ae14    # 0.78f

    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$pG-SLx9dugH4cqe5Yg9zJYGZO10(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    const/high16 p0, 0x40000

    .line 287
    invoke-static {p0}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    const/4 p0, 0x0

    return p0
.end method

.method public static synthetic $r8$lambda$q6hwrFcwlRx4ekaWdAlAGU3xaYo(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    .line 301
    const/high16 p0, 0x40000000    # 2.0f

    return p0
.end method

.method public static synthetic $r8$lambda$u9331ymO4dSaP3a7W9JEn0Yi-iY(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    const/16 p0, 0x100

    .line 119
    invoke-static {p0}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p0

    if-nez p0, :cond_0

    .line 120
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultSubmenuBackground:I

    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    return p0

    .line 122
    :cond_0
    sget p0, Lorg/telegram/ui/ActionBar/Theme;->key_actionBarDefaultSubmenuBackground:I

    invoke-static {p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(I)I

    move-result p0

    if-eqz p1, :cond_1

    const p1, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_1
    const p1, 0x3f533333    # 0.825f

    :goto_0
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$wfoIaOdccp5qam9I3rr5JeZYqrU(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 2

    const/high16 p1, 0x40000

    .line 28
    invoke-static {p1}, Lorg/telegram/messenger/LiteMode;->isEnabled(I)Z

    move-result p1

    if-eqz p1, :cond_0

    const p1, 0x3f59999a    # 0.85f

    goto :goto_0

    :cond_0
    const p1, 0x3f428f5c    # 0.76f

    .line 29
    :goto_0
    sget v0, Lorg/telegram/ui/ActionBar/Theme;->key_windowBackgroundWhite:I

    invoke-static {v0, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result v0

    .line 30
    sget v1, Lorg/telegram/ui/ActionBar/Theme;->key_glass_targetMainTabs:I

    invoke-static {v1, p0}, Lorg/telegram/ui/ActionBar/Theme;->getColor(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)I

    move-result p0

    .line 31
    invoke-static {v0, p0, p1}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->solveSrcColor(IIF)I

    move-result p0

    return p0
.end method

.method public static synthetic $r8$lambda$xX6LfYFnk7J-GyLTGNY7oUWQ2ok(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Z)I
    .locals 0

    const/high16 p0, -0x1000000

    const p1, 0x3d99999a    # 0.075f

    .line 102
    invoke-static {p0, p1}, Lorg/telegram/ui/ActionBar/Theme;->multAlpha(IF)I

    move-result p0

    return p0
.end method

.method public static attachMenuSearch(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 3

    .line 145
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda4;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda4;-><init>()V

    .line 146
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x17000000

    const v1, 0x17ffffff

    .line 151
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 152
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x11000000

    const v1, 0x4ffffff

    .line 153
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x40000000    # 2.0f

    .line 154
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const v1, 0x3eaaaaab

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    const/4 v2, 0x0

    invoke-virtual {p0, v0, v2, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3ecccccd    # 0.4f

    .line 155
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 156
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static bottomPanelChatActivity(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 170
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance v1, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda16;

    invoke-direct {v1, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda16;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 171
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x28ffffff

    const/4 v1, -0x1

    .line 180
    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x14ffffff

    .line 181
    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x20000000

    const/4 v1, 0x0

    .line 182
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x3f000000    # 0.5f

    .line 184
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 185
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static bulletin(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 250
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda17;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda17;-><init>()V

    .line 251
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x3f000000    # 0.5f

    .line 260
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 261
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static checkBlurEnabled(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z
    .locals 3

    if-eqz p1, :cond_0

    .line 365
    invoke-interface {p1}, Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;->isDark()Z

    move-result p1

    goto :goto_0

    :cond_0
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->isCurrentThemeDark()Z

    move-result p1

    .line 367
    :goto_0
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->chatBlurEnabled()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    if-nez p1, :cond_1

    .line 369
    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v2

    iget-object v2, v2, Lorg/telegram/messenger/MessagesController;->config:Lorg/telegram/messenger/AppGlobalConfig;

    iget-object v2, v2, Lorg/telegram/messenger/AppGlobalConfig;->disableBlurInLightTheme:Lorg/telegram/messenger/AppGlobalConfig$ConfigBoolean;

    invoke-virtual {v2}, Lorg/telegram/messenger/AppGlobalConfig$ConfigBoolean;->get()Z

    move-result v2

    if-eqz v2, :cond_1

    move v0, v1

    :cond_1
    if-eqz v0, :cond_2

    if-eqz p1, :cond_2

    .line 374
    invoke-static {p0}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/messenger/MessagesController;->config:Lorg/telegram/messenger/AppGlobalConfig;

    iget-object p0, p0, Lorg/telegram/messenger/AppGlobalConfig;->disableBlurInDarkTheme:Lorg/telegram/messenger/AppGlobalConfig$ConfigBoolean;

    invoke-virtual {p0}, Lorg/telegram/messenger/AppGlobalConfig$ConfigBoolean;->get()Z

    move-result p0

    if-eqz p0, :cond_2

    return v1

    :cond_2
    return v0
.end method

.method public static checkBlurEnabled(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z
    .locals 1

    .line 361
    sget v0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->checkBlurEnabled(ILorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Z

    move-result p0

    return p0
.end method

.method public static counterMini(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 98
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda10;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda10;-><init>()V

    .line 99
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x60ffffff

    const v1, 0x50ffffff

    .line 104
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x24000000

    .line 105
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, 0x0

    .line 106
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, 0x0

    .line 107
    invoke-virtual {p0, v0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3edc28f6    # 0.43f

    .line 108
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 109
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static emojiViewButton(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 3

    .line 83
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda12;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda12;-><init>()V

    .line 84
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x28ffffff

    const/4 v1, -0x1

    .line 89
    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x14ffffff

    .line 90
    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x40000000    # 2.0f

    const/4 v1, 0x0

    .line 91
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x406aaaab

    .line 92
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const v1, 0x3f2aaaab

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    const/4 v2, 0x0

    invoke-virtual {p0, v0, v2, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x3f000000    # 0.5f

    .line 93
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 94
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static inputFieldDialogActivity(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 0

    .line 265
    invoke-static {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl;->topPanel(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static inputFieldShareAlert(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 4

    .line 269
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda1;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda1;-><init>()V

    .line 270
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x28ffffff

    .line 276
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x14ffffff

    .line 277
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x20000000

    const/4 v1, 0x0

    .line 278
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x40555555

    .line 279
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const v1, 0x3f2aaaab

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v2

    const/4 v3, 0x0

    invoke-virtual {p0, v0, v3, v2}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x3f800000    # 1.0f

    .line 280
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 281
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static mainTabs(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 3

    .line 26
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda3;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda3;-><init>()V

    .line 27
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x20000000

    const v1, 0x4ffffff

    .line 33
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x402ab021    # 2.667f

    .line 34
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const v1, 0x3f59999a    # 0.85f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    const/4 v2, 0x0

    invoke-virtual {p0, v0, v2, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 36
    invoke-static {p0}, Lcom/exteragram/messenger/utils/ui/MainTabsUiHelper;->applyBackgroundStroke(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;)V

    .line 38
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static messageMenuBackground(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 117
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda13;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda13;-><init>()V

    .line 118
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x44ffffff    # 2047.9999f

    const/4 v1, 0x0

    .line 124
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x22ffffff

    .line 125
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x38000000

    .line 126
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x40600000    # 3.5f

    .line 127
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3f2aaaab

    .line 128
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 129
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static photoViewer(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 285
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda11;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda11;-><init>()V

    .line 286
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x28ffffff

    .line 292
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v1, 0x14ffffff

    .line 293
    invoke-virtual {p0, v1, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 294
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorFull(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3f2aaaab

    .line 295
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 296
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static photoViewerMenu(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 300
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda18;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda18;-><init>()V

    .line 301
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x28ffffff

    .line 302
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x14ffffff

    .line 303
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3f2aaaab

    .line 304
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 305
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static premiumButton(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 309
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda8;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda8;-><init>()V

    .line 310
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, -0x1

    const v1, 0x20ffffff

    .line 312
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, 0x0

    .line 313
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x48000000    # 131072.0f

    const v1, 0x4ffffff

    .line 314
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3f2aaaab

    .line 315
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3f2b851f    # 0.67f

    .line 316
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 317
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static scrimMenuBackground(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 133
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda2;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda2;-><init>()V

    .line 134
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x44ffffff    # 2047.9999f

    const/4 v1, 0x0

    .line 136
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x22ffffff

    .line 137
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x26000000

    .line 138
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x40800000    # 4.0f

    .line 139
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const/4 v1, 0x0

    invoke-virtual {p0, v0, v1, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3f2aaaab

    .line 140
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 141
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static searchFloatingDate(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 160
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda15;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda15;-><init>()V

    .line 161
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x17000000

    const v1, 0x17ffffff

    .line 162
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 163
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, 0x0

    .line 164
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x3f800000    # 1.0f

    .line 165
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 166
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static shadow(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 3

    .line 321
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    const p0, 0x28ffffff

    const/4 v1, 0x0

    .line 322
    invoke-virtual {v0, v1, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x14ffffff

    .line 323
    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x30000000

    const v1, 0x4ffffff

    .line 324
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x40800000    # 4.0f

    .line 325
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const v1, 0x3eaaaaab

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    const/4 v2, 0x0

    invoke-virtual {p0, v0, v2, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3ecccccd    # 0.4f

    .line 326
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 327
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static solveSrcColor(IIF)I
    .locals 7

    const/4 v0, 0x0

    const/high16 v1, 0x3f800000    # 1.0f

    .line 331
    invoke-static {p2, v0, v1}, Landroidx/core/math/MathUtils;->clamp(FFF)F

    move-result p2

    cmpg-float v0, p2, v0

    const/4 v2, 0x0

    if-gtz v0, :cond_0

    .line 335
    invoke-static {v2, v2, v2, v2}, Landroid/graphics/Color;->argb(IIII)I

    move-result p0

    return p0

    :cond_0
    cmpl-float v0, p2, v1

    const/16 v3, 0xff

    if-ltz v0, :cond_1

    .line 338
    invoke-static {p1}, Landroid/graphics/Color;->red(I)I

    move-result p0

    invoke-static {p1}, Landroid/graphics/Color;->green(I)I

    move-result p2

    invoke-static {p1}, Landroid/graphics/Color;->blue(I)I

    move-result p1

    invoke-static {v3, p0, p2, p1}, Landroid/graphics/Color;->argb(IIII)I

    move-result p0

    return p0

    .line 341
    :cond_1
    invoke-static {p0}, Landroid/graphics/Color;->red(I)I

    move-result v0

    .line 342
    invoke-static {p0}, Landroid/graphics/Color;->green(I)I

    move-result v4

    .line 343
    invoke-static {p0}, Landroid/graphics/Color;->blue(I)I

    move-result p0

    .line 345
    invoke-static {p1}, Landroid/graphics/Color;->red(I)I

    move-result v5

    .line 346
    invoke-static {p1}, Landroid/graphics/Color;->green(I)I

    move-result v6

    .line 347
    invoke-static {p1}, Landroid/graphics/Color;->blue(I)I

    move-result p1

    sub-float/2addr v1, p2

    int-to-float v5, v5

    int-to-float v0, v0

    mul-float/2addr v0, v1

    sub-float/2addr v5, v0

    div-float/2addr v5, p2

    .line 351
    invoke-static {v5}, Ljava/lang/Math;->round(F)I

    move-result v0

    invoke-static {v0, v2, v3}, Landroidx/core/math/MathUtils;->clamp(III)I

    move-result v0

    int-to-float v5, v6

    int-to-float v4, v4

    mul-float/2addr v4, v1

    sub-float/2addr v5, v4

    div-float/2addr v5, p2

    .line 352
    invoke-static {v5}, Ljava/lang/Math;->round(F)I

    move-result v4

    invoke-static {v4, v2, v3}, Landroidx/core/math/MathUtils;->clamp(III)I

    move-result v4

    int-to-float p1, p1

    int-to-float p0, p0

    mul-float/2addr p0, v1

    sub-float/2addr p1, p0

    div-float/2addr p1, p2

    .line 353
    invoke-static {p1}, Ljava/lang/Math;->round(F)I

    move-result p0

    invoke-static {p0, v2, v3}, Landroidx/core/math/MathUtils;->clamp(III)I

    move-result p0

    const/high16 p1, 0x437f0000    # 255.0f

    mul-float/2addr p2, p1

    .line 355
    invoke-static {p2}, Ljava/lang/Math;->round(F)I

    move-result p1

    invoke-static {p1, v2, v3}, Landroidx/core/math/MathUtils;->clamp(III)I

    move-result p1

    .line 357
    invoke-static {p1, v0, v4, p0}, Landroid/graphics/Color;->argb(IIII)I

    move-result p0

    return p0
.end method

.method public static subFloatingButton(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 3

    .line 58
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda14;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda14;-><init>()V

    .line 59
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x11000000

    const v1, 0x6ffffff

    .line 63
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x20000000

    const v1, 0x11ffffff

    .line 64
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 65
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->getSubFabShadowColor()I

    move-result v0

    const v1, 0x4ffffff

    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 66
    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->getSubFabShadowRadiusDp()F

    move-result v0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-static {}, Lcom/exteragram/messenger/utils/ui/FabUiHelper;->getSubFabShadowDyDp()F

    move-result v1

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    const/4 v2, 0x0

    invoke-virtual {p0, v0, v2, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3ecccccd    # 0.4f

    .line 67
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 68
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowAlwaysVisible()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 69
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static tabsPanelNoShadow(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 1

    .line 73
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda19;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda19;-><init>()V

    .line 74
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, 0x0

    .line 76
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 77
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 78
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 79
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static topPanel(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 3

    .line 42
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda0;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda0;-><init>()V

    .line 43
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x11000000

    const v1, 0x6ffffff

    .line 49
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x11ffffff

    const/high16 v1, 0x20000000

    .line 50
    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x4ffffff

    .line 51
    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x402ab021    # 2.667f

    .line 52
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    const v1, 0x3f59999a    # 0.85f

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    const/4 v2, 0x0

    invoke-virtual {p0, v0, v2, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3ecccccd    # 0.4f

    .line 53
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 54
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static topPanelChatActivity(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 189
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance v1, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda6;

    invoke-direct {v1, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 190
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x20ffffff

    const/4 v1, -0x1

    .line 203
    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x14ffffff

    .line 204
    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda7;

    invoke-direct {v0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda7;-><init>()V

    .line 205
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorFull(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/high16 v0, 0x20000000

    const/4 v1, 0x0

    .line 206
    invoke-virtual {p0, v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const v0, 0x3f0ccccd    # 0.55f

    .line 208
    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v1

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->dpf2(F)F

    move-result v0

    invoke-virtual {p0, v1, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 209
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static topPanelChatActivitySearchListBg(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 1

    .line 234
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance p0, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda9;

    invoke-direct {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda9;-><init>()V

    .line 235
    invoke-virtual {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, 0x0

    .line 240
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 241
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 242
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorFull(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 243
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, 0x0

    .line 244
    invoke-virtual {p0, v0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 245
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 246
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method

.method public static topPanelChatActivityTags(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;
    .locals 2

    .line 213
    new-instance v0, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    invoke-direct {v0, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    new-instance v1, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda5;

    invoke-direct {v1, p0}, Lorg/telegram/ui/Components/blur3/drawable/color/impl/BlurredBackgroundProviderImpl$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V

    .line 214
    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setBackgroundColor(Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder$ColorProvider;)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, 0x0

    .line 224
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorTop(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 225
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorBottom(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 226
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeColorFull(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 227
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowColor(II)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    const/4 v0, 0x0

    .line 228
    invoke-virtual {p0, v0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setShadowLayer(FFF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 229
    invoke-virtual {p0, v0, v0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->setStrokeWidth(FF)Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;

    move-result-object p0

    .line 230
    invoke-virtual {p0}, Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProviderBuilder;->build()Lorg/telegram/ui/Components/blur3/drawable/color/BlurredBackgroundProvider;

    move-result-object p0

    return-object p0
.end method
