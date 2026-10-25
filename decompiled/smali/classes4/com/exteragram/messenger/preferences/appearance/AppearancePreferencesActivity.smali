.class public final Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;,
        Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$Companion;,
        Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0007\n\u0002\u0008\u0013\u0018\u0000 A2\u00020\u0001:\u0002ABB\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u001c\u001a\u00020\u001dH\u0014J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0014J\u0008\u0010\"\u001a\u00020#H\u0016J\u001e\u0010$\u001a\u00020\u001d2\u000c\u0010%\u001a\u0008\u0012\u0004\u0012\u00020\'0&2\u0006\u0010(\u001a\u00020)H\u0014J0\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\'2\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000200H\u0014J\u0008\u00102\u001a\u00020\'H\u0002J\u0010\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020.H\u0002J\u0008\u00105\u001a\u00020\u001dH\u0002J\u0008\u00106\u001a\u00020\u001dH\u0002J\u0008\u00107\u001a\u00020\u001dH\u0002J\u0008\u00108\u001a\u00020\u001dH\u0002J\u0008\u00109\u001a\u00020\u001dH\u0002J\u0008\u0010:\u001a\u00020\u001dH\u0002J\u0008\u0010;\u001a\u00020\u0019H\u0002J\u0008\u0010<\u001a\u00020\u001dH\u0002J\u0008\u0010=\u001a\u00020\u001dH\u0002J\u0008\u0010>\u001a\u00020\u001dH\u0002J\u0008\u0010?\u001a\u00020\u001dH\u0002J\u0010\u0010@\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\'H\u0002R\u0016\u0010\u0004\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u0008\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\t\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\n\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\u000b\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\u000c\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006C"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "titles",
        "",
        "",
        "[Ljava/lang/CharSequence;",
        "tabIconsOptions",
        "tabCounterModes",
        "dividerStyles",
        "glassOutlineStyles",
        "avatarCornersPreviewCell",
        "Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;",
        "chatListPreviewCell",
        "Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;",
        "filterTabsPreviewCell",
        "Lcom/exteragram/messenger/preferences/appearance/components/FilterTabsPreviewCell;",
        "fabShapeCell",
        "Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;",
        "appIconDrawableIcon",
        "Lcom/exteragram/messenger/appicons/AppIcon;",
        "appIconDrawableShape",
        "",
        "appIconDrawable",
        "Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;",
        "md3Styles",
        "Lcom/exteragram/messenger/preferences/SwitchGroup;",
        "initializeOptionStrings",
        "",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "getTitle",
        "",
        "fillItems",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "position",
        "",
        "x",
        "",
        "y",
        "createSectionRadiusSliderItem",
        "formatSectionRadius",
        "value",
        "handleDividerStyleChange",
        "handleSectionRadiusChange",
        "updateMD3Styles",
        "handleActionBarTitleClick",
        "handleTabTitleClick",
        "handleTabCounterClick",
        "getAppIconDrawable",
        "handleHideAllChatsClick",
        "handleUseSystemFontsClick",
        "handleForceBlurChange",
        "handleGlassMessageMenuChange",
        "handleUseSystemEmojiClick",
        "Companion",
        "AppearanceItem",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$Companion;


# instance fields
.field private appIconDrawable:Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;

.field private appIconDrawableIcon:Lcom/exteragram/messenger/appicons/AppIcon;

.field private appIconDrawableShape:Z

.field private avatarCornersPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;

.field private chatListPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;

.field private dividerStyles:[Ljava/lang/CharSequence;

.field private fabShapeCell:Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;

.field private filterTabsPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/FilterTabsPreviewCell;

.field private glassOutlineStyles:[Ljava/lang/CharSequence;

.field private final md3Styles:Lcom/exteragram/messenger/preferences/SwitchGroup;

.field private tabCounterModes:[Ljava/lang/CharSequence;

.field private tabIconsOptions:[Ljava/lang/CharSequence;

.field private titles:[Ljava/lang/CharSequence;


# direct methods
.method public static $r8$lambda$1ca0TsQHxg_ve-Cd4fD2LnWERcQ(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;I)V
    .locals 1

    .line 293
    invoke-static {}, Lcom/exteragram/messenger/DividerStyle;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/DividerStyle;

    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setDividerStyle(Lcom/exteragram/messenger/DividerStyle;)V

    .line 294
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDividerStyle()Lcom/exteragram/messenger/DividerStyle;

    move-result-object p1

    sget-object v0, Lcom/exteragram/messenger/DividerStyle;->SEGMENTS:Lcom/exteragram/messenger/DividerStyle;

    if-ne p1, v0, :cond_0

    const/4 p1, 0x1

    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setSectionsSeparatedHeaders(Z)V

    .line 295
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleDividerStyleChange()V

    return-void
.end method

.method public static $r8$lambda$1i_bKYI7Svw2f89kxHihDzOOCPw(Z)V
    .locals 0

    .line 320
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setGlassMessageMenu(Z)V

    return-void
.end method

.method public static $r8$lambda$3oT2MwPy8dc-GSiyp6BtbgfQwGQ(Z)V
    .locals 0

    .line 280
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideAllChats(Z)V

    return-void
.end method

.method public static $r8$lambda$8MK4p-OYTj227hoMiyoUvZFq2ZU(Z)V
    .locals 0

    .line 316
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setForceBlur(Z)V

    return-void
.end method

.method public static $r8$lambda$8sCJUOy7Prq_rFsAIvofP19hxxI(Z)V
    .locals 0

    .line 288
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setSectionsSeparatedHeaders(Z)V

    return-void
.end method

.method public static $r8$lambda$BQktZMK8L3x7TpZfpqssg2gNJSE(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;Ljava/lang/Integer;)V
    .locals 0

    .line 335
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    int-to-float p1, p1

    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setSectionRadius(F)V

    .line 336
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleSectionRadiusChange()V

    return-void
.end method

.method public static $r8$lambda$BWpsglEKp91Y3TBO2mIytxIjNyw(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;I)V
    .locals 1

    .line 276
    invoke-static {}, Lcom/exteragram/messenger/TabCounterMode;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/TabCounterMode;

    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setTabCounterMode(Lcom/exteragram/messenger/TabCounterMode;)V

    .line 277
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleTabCounterClick()V

    return-void
.end method

.method public static $r8$lambda$BroMj8zTOlcCXqcxN3abB-0FVwI(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;Landroid/view/View;)V
    .locals 2

    .line 193
    instance-of v0, p1, Lorg/telegram/ui/Cells/TextCell;

    if-nez v0, :cond_0

    return-void

    .line 194
    :cond_0
    check-cast p1, Lorg/telegram/ui/Cells/TextCell;

    .line 195
    invoke-virtual {p1}, Lorg/telegram/ui/Cells/TextCell;->getValueImageView()Landroid/widget/ImageView;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 196
    invoke-virtual {p1}, Lorg/telegram/ui/Cells/TextCell;->getValueImageView()Landroid/widget/ImageView;

    move-result-object v0

    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->getAppIconDrawable()Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;

    move-result-object p0

    invoke-virtual {v0, p0}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 197
    iget p0, p1, Lorg/telegram/ui/Cells/TextCell;->heightDp:I

    add-int/lit8 p0, p0, -0x22

    div-int/lit8 p0, p0, 0x2

    iput p0, p1, Lorg/telegram/ui/Cells/TextCell;->valueImageRight:I

    return-void
.end method

.method public static $r8$lambda$F4Fe_nJRTIDZDn3cVyuEZIxTvYI(Z)V
    .locals 0

    .line 254
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setCenterTitle(Z)V

    return-void
.end method

.method public static $r8$lambda$Lqn7yqkMgPDCvNKpHuRzAydUlIE(Z)V
    .locals 0

    .line 235
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setSingleCornerRadius(Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$LvtQV71VS-Iyx7gXN2zM6odocTU(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;I)Ljava/lang/CharSequence;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->formatSectionRadius(I)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$O_77vuOi_-yVX3xqwoH81AIX8Gg(Z)V
    .locals 0

    .line 260
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideFloatingButton(Z)V

    return-void
.end method

.method public static $r8$lambda$R6BuQrMpk6ELdoJwdYr15BU-lVE(Z)V
    .locals 0

    .line 298
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setUseSystemFonts(Z)V

    return-void
.end method

.method public static $r8$lambda$TOvQ7PIAyv4Dww5dvkxIWgyKYV0(Z)V
    .locals 0

    .line 305
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setCustomThemes(Z)V

    return-void
.end method

.method public static $r8$lambda$aWZ13KjUGE1ZDBxm6o6bnXoUZog(Z)V
    .locals 0

    .line 245
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideStories(Z)V

    return-void
.end method

.method public static $r8$lambda$adkCZXH_7cfSg-0UHOTeb2dnYio(Z)V
    .locals 0

    .line 268
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setSenderMiniAvatars(Z)V

    return-void
.end method

.method public static $r8$lambda$bnTvQSZ4KR0iSISWLhmrYR3liWM(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;I)V
    .locals 0

    .line 240
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setTitleText(I)V

    .line 241
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleActionBarTitleClick()V

    return-void
.end method

.method public static $r8$lambda$diPBCnaehDMw2zIgTWBxe4Djmyc(Z)V
    .locals 0

    .line 264
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideDialogsSearchBar(Z)V

    return-void
.end method

.method public static $r8$lambda$kJCSt1WlfaYfeCZTu0oGE2JtegI(Z)V
    .locals 0

    .line 307
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setForceSnow(Z)V

    return-void
.end method

.method public static $r8$lambda$kaybrtE5Evj9ZdKXmh8rIZuvcFg(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;I)V
    .locals 1

    .line 271
    invoke-static {}, Lcom/exteragram/messenger/TabIconsMode;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/TabIconsMode;

    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setTabIcons(Lcom/exteragram/messenger/TabIconsMode;)V

    .line 272
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleTabTitleClick()V

    return-void
.end method

.method public static $r8$lambda$o0XG8-7MBSw9iCPkdSYz77htMTU(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;I)V
    .locals 1

    .line 312
    invoke-static {}, Lcom/exteragram/messenger/GlassOutlineStyle;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/GlassOutlineStyle;

    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setGlassOutlineStyle(Lcom/exteragram/messenger/GlassOutlineStyle;)V

    .line 313
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 p1, 0x0

    invoke-interface {p0, p1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method public static $r8$lambda$wN3nROGrT1gfQxqZrXvRJ6FnN4E(Z)V
    .locals 0

    .line 249
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setHideActionBarStatus(Z)V

    return-void
.end method

.method public static $r8$lambda$ztcBy6t6GatknCPYwJLgnrCl0ns(Z)V
    .locals 0

    .line 304
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setGooeyAvatarAnimation(Z)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->Companion:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 9

    .line 48
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 112
    new-instance v0, Lcom/exteragram/messenger/preferences/SwitchGroup;

    sget-object v2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->MD3_STYLES:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v3, Lorg/telegram/messenger/R$string;->MaterialDesign3:I

    const/16 v7, 0x28

    const/4 v8, 0x0

    const/4 v4, 0x0

    const-string v5, "md3Styles"

    const/4 v6, 0x0

    move-object v1, p0

    invoke-direct/range {v0 .. v8}, Lcom/exteragram/messenger/preferences/SwitchGroup;-><init>(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 113
    new-instance v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$1;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$1;-><init>(Ljava/lang/Object;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onChanged(Lkotlin/jvm/functions/Function0;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 114
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->NEW_LOADING_STYLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->NewLoadingStyle:I

    sget-object v3, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$2;->INSTANCE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$2;

    const/16 v5, 0x8

    const/4 v6, 0x0

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 115
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->NEW_SLIDER_STYLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->NewSliderStyle:I

    sget-object v3, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$3;->INSTANCE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$3;

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 116
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->NEW_SWITCH_STYLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->NewSwitchStyle:I

    sget-object v3, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$4;->INSTANCE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$4;

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 117
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->NEW_CHAT_HEADER_STYLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->ChatHeader:I

    sget-object v3, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$5;->INSTANCE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$5;

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 118
    const-string v1, "Appearance-M3Styles-ChatHeader"

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->markNew(Ljava/lang/String;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 119
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->NEW_NAVIGATION_BAR_STYLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->BottomNavigationBarMode:I

    sget-object v3, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$6;->INSTANCE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$6;

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 120
    const-string v1, "Appearance-M3Styles-NavigationBar"

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->markNew(Ljava/lang/String;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 121
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->NEW_FAB_STYLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->NewFabStyle:I

    sget-object v3, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$7;->INSTANCE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$md3Styles$1$7;

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/preferences/SwitchGroup;->add$default(Lcom/exteragram/messenger/preferences/SwitchGroup;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;ILjava/lang/Object;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 122
    const-string v1, "Appearance-M3Styles-FloatingButton"

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->markNew(Ljava/lang/String;)Lcom/exteragram/messenger/preferences/SwitchGroup;

    .line 112
    iput-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->md3Styles:Lcom/exteragram/messenger/preferences/SwitchGroup;

    return-void
.end method

.method public static final synthetic access$getParentLayout$p$s1161066243(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)Lorg/telegram/ui/ActionBar/INavigationLayout;
    .locals 0

    .line 48
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    return-object p0
.end method

.method public static final synthetic access$updateMD3Styles(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V
    .locals 0

    .line 48
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->updateMD3Styles()V

    return-void
.end method

.method private final createSectionRadiusSliderItem()Lorg/telegram/ui/Components/UItem;
    .locals 6

    .line 331
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSectionRadiusDp()I

    move-result v2

    .line 333
    new-instance v4, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda22;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda22;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    .line 328
    new-instance v5, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda23;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda23;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    const/4 v0, 0x1

    const/4 v1, 0x0

    const/16 v3, 0x1c

    invoke-static/range {v0 .. v5}, Lorg/telegram/ui/Components/UItem;->asIntSlideView(IIIILorg/telegram/messenger/Utilities$CallbackReturn;Lorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    .line 338
    sget-object v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->SECTION_RADIUS:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->getId()I

    move-result v0

    iput v0, p0, Lorg/telegram/ui/Components/UItem;->id:I

    .line 339
    sget v0, Lorg/telegram/messenger/R$string;->Sections:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    return-object p0
.end method

.method private final formatSectionRadius(I)Ljava/lang/CharSequence;
    .locals 0

    if-eqz p1, :cond_1

    const/16 p0, 0x1c

    if-eq p1, p0, :cond_0

    .line 346
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, " dp"

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 345
    :cond_0
    sget p0, Lorg/telegram/messenger/R$string;->PredictiveBackMax:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 344
    :cond_1
    sget p0, Lorg/telegram/messenger/R$string;->BlurOff:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final getAppIconDrawable()Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;
    .locals 4

    .line 388
    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getSelectedIcon()Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v0

    .line 389
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseSystemIconShape()Z

    move-result v1

    .line 390
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->appIconDrawable:Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;

    if-eqz v2, :cond_1

    .line 391
    iget-object v3, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->appIconDrawableIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    invoke-static {v3, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    iget-boolean v3, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->appIconDrawableShape:Z

    if-eq v3, v1, :cond_0

    goto :goto_0

    :cond_0
    return-object v2

    .line 392
    :cond_1
    :goto_0
    iput-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->appIconDrawableIcon:Lcom/exteragram/messenger/appicons/AppIcon;

    .line 393
    iput-boolean v1, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->appIconDrawableShape:Z

    .line 394
    new-instance v1, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;

    const/high16 v2, 0x42080000    # 34.0f

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->dp(F)I

    move-result v2

    invoke-direct {v1, v0, v2}, Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;-><init>(Lcom/exteragram/messenger/appicons/AppIcon;I)V

    .line 395
    iput-object v1, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->appIconDrawable:Lcom/exteragram/messenger/appicons/AppIconPreviewDrawable;

    return-object v1
.end method

.method private final handleActionBarTitleClick()V
    .locals 2

    .line 375
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->chatListPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;->updateStatus(Z)V

    .line 376
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget v0, Lorg/telegram/messenger/NotificationCenter;->currentUserPremiumStatusChanged:I

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p0, v0, v1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method private final handleDividerStyleChange()V
    .locals 1

    .line 350
    invoke-static {}, Lorg/telegram/ui/ActionBar/Theme;->applyCommonTheme()V

    .line 351
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    .line 352
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    invoke-virtual {v0}, Landroidx/recyclerview/widget/RecyclerView;->invalidateItemDecorations()V

    .line 353
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->avatarCornersPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;->invalidate()V

    .line 354
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->chatListPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;->invalidate()V

    .line 355
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->fabShapeCell:Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;->invalidate()V

    .line 356
    :cond_2
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->filterTabsPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/FilterTabsPreviewCell;

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    .line 357
    :cond_3
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v0, 0x0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method private final handleForceBlurChange()V
    .locals 0

    .line 411
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->chatBlurEnabled()Z

    move-result p0

    if-nez p0, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getForceBlur()Z

    move-result p0

    if-eqz p0, :cond_0

    .line 412
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->toggleChatBlur()V

    :cond_0
    return-void
.end method

.method private final handleGlassMessageMenuChange()V
    .locals 4

    .line 416
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGlassMessageMenu()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->chatBlurEnabled()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 417
    :cond_0
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 418
    sget v0, Lorg/telegram/messenger/R$raw;->info:I

    .line 419
    sget v1, Lorg/telegram/messenger/R$string;->GlassMessageMenuBlurOff:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 420
    sget v2, Lorg/telegram/messenger/R$string;->Enable:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 421
    new-instance v3, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda21;

    invoke-direct {v3}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda21;-><init>()V

    .line 417
    invoke-virtual {p0, v0, v1, v2, v3}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 422
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_1
    :goto_0
    return-void
.end method

.method private final handleHideAllChatsClick()V
    .locals 4

    .line 401
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->dialogFiltersUpdated:I

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v3}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 402
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget v0, Lorg/telegram/messenger/NotificationCenter;->mainUserInfoChanged:I

    new-array v1, v2, [Ljava/lang/Object;

    invoke-virtual {p0, v0, v1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method private final handleSectionRadiusChange()V
    .locals 1

    .line 361
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object v0

    .line 362
    invoke-virtual {v0}, Lorg/telegram/ui/Components/UniversalRecyclerView;->setSections()V

    .line 363
    invoke-virtual {v0}, Landroid/view/View;->invalidate()V

    .line 364
    invoke-virtual {v0}, Landroidx/recyclerview/widget/RecyclerView;->invalidateItemDecorations()V

    .line 366
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v0, 0x0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method private final handleTabCounterClick()V
    .locals 2

    .line 383
    sget-object v0, Lcom/exteragram/messenger/utils/chats/FolderCounters;->Companion:Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/utils/chats/FolderCounters$Companion;->recountAll()V

    .line 384
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget v0, Lorg/telegram/messenger/NotificationCenter;->dialogFiltersUpdated:I

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p0, v0, v1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method private final handleTabTitleClick()V
    .locals 2

    .line 380
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget v0, Lorg/telegram/messenger/NotificationCenter;->dialogFiltersUpdated:I

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p0, v0, v1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method private final handleUseSystemEmojiClick(Lorg/telegram/ui/Components/UItem;)V
    .locals 2

    .line 426
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->toggleUseSystemEmoji()V

    .line 427
    sget-boolean v0, Lorg/telegram/messenger/SharedConfig;->useSystemEmoji:Z

    const/4 v1, 0x1

    xor-int/2addr v0, v1

    invoke-virtual {p1, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    .line 428
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v0, 0x0

    invoke-interface {p1, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    .line 429
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p0, :cond_0

    invoke-virtual {p0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method

.method private final handleUseSystemFontsClick()V
    .locals 1

    .line 406
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->clearTypefaceCache()V

    .line 407
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v0, 0x1

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method private final updateMD3Styles()V
    .locals 1

    .line 370
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->avatarCornersPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;->updateSliderStyle()V

    .line 371
    :cond_0
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    const/4 v0, 0x0

    invoke-interface {p0, v0}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 4

    .line 155
    new-instance v0, Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;

    iget-object v1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->resourceProvider:Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;

    const-string v2, "preferences_preview_style"

    const/4 v3, 0x0

    invoke-static {v2, v3}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->getIntConfigValue(Ljava/lang/String;I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Number;->intValue()I

    move-result v2

    invoke-direct {v0, p1, p0, v1, v2}, Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;-><init>(Landroid/content/Context;Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;I)V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->avatarCornersPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;

    .line 156
    new-instance v0, Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->chatListPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;

    .line 157
    new-instance v0, Lcom/exteragram/messenger/preferences/appearance/components/FilterTabsPreviewCell;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/preferences/appearance/components/FilterTabsPreviewCell;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->filterTabsPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/FilterTabsPreviewCell;

    .line 158
    new-instance v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$createView$1;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$createView$1;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->fabShapeCell:Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;

    .line 161
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object p0

    return-object p0
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 16
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            ")V"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v14, p1

    .line 167
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->AVATAR_CORNERS_PREVIEW:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->getId()I

    move-result v1

    iget-object v2, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->avatarCornersPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/AvatarCornersPreviewCell;

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    const-string v2, "avatarCorners"

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 168
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->SINGLE_CORNER_RADIUS:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->SingleCornerRadius:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSingleCornerRadius()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v11, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "singleCornerRadius"

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 169
    sget v1, Lorg/telegram/messenger/R$string;->SingleCornerRadiusInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 171
    sget v1, Lorg/telegram/messenger/R$string;->ListOfChats:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 172
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->CHAT_LIST_PREVIEW:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->getId()I

    move-result v1

    iget-object v2, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->chatListPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 173
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->FORCE_SNOW:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->ForceSnow:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getForceSnow()Z

    move-result v3

    sget v4, Lorg/telegram/messenger/R$string;->ForceSnowInfo:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1b0

    const-string v7, "forceSnow"

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 174
    invoke-virtual/range {p0 .. p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getUserConfig()Lorg/telegram/messenger/UserConfig;

    move-result-object v0

    invoke-virtual {v0}, Lorg/telegram/messenger/UserConfig;->isPremium()Z

    move-result v12

    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->HIDE_ACTION_BAR_STATUS:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideActionBarStatus:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideActionBarStatus()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const-string v7, "hideActionBarStatus"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-static {v14, v12, v1}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 175
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->CENTER_TITLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->CenterTitle:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCenterTitle()Z

    move-result v3

    const-string v7, "centerTitle"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 176
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->HIDE_STORIES:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideStories:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideStories()Z

    move-result v3

    const-string v7, "hideStories"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 177
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->HIDE_FLOATING_BUTTON:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideFloatingButton:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideFloatingButton()Z

    move-result v3

    const-string v7, "hideFloatingButton"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 178
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->HIDE_DIALOGS_SEARCH_BAR:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideDialogsSearchBar:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideDialogsSearchBar()Z

    move-result v3

    const-string v7, "hideDialogsSearchBar"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 179
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->SENDER_MINI_AVATARS:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->SenderMiniAvatars:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSenderMiniAvatars()Z

    move-result v3

    const-string v7, "senderMiniAvatars"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 180
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->ACTION_BAR_TITLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->ActionBarTitle:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->titles:[Ljava/lang/CharSequence;

    const/4 v15, 0x0

    if-nez v3, :cond_0

    move-object v3, v15

    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTitleText()I

    move-result v4

    aget-object v4, v3, v4

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "actionBarTitle"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 181
    sget v1, Lorg/telegram/messenger/R$string;->ListOfChatsInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 183
    sget v1, Lorg/telegram/messenger/R$string;->Filters:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 184
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->FOLDERS_PREVIEW:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->getId()I

    move-result v1

    iget-object v2, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->filterTabsPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/FilterTabsPreviewCell;

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 185
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->TAB_TITLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->TabTitleStyle:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->tabIconsOptions:[Ljava/lang/CharSequence;

    if-nez v3, :cond_1

    move-object v3, v15

    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/TabIconsMode;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v4

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTabIcons()Lcom/exteragram/messenger/TabIconsMode;

    move-result-object v5

    invoke-interface {v4, v5}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result v4

    aget-object v4, v3, v4

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "tabTitleStyle"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 186
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->TAB_COUNTER:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->TabCounter:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->tabCounterModes:[Ljava/lang/CharSequence;

    if-nez v3, :cond_2

    move-object v3, v15

    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTabCounterMode()Lcom/exteragram/messenger/TabCounterMode;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

    move-result v4

    aget-object v4, v3, v4

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const-string v8, "tabCounter"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 187
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->HIDE_ALL_CHATS:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->HideAllChats:I

    sget v2, Lorg/telegram/messenger/R$string;->FilterAllChats:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    filled-new-array {v2}, [Ljava/lang/Object;

    move-result-object v2

    invoke-static {v0, v2}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getHideAllChats()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v8, 0x0

    const-string v7, "hideAllChats"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 188
    sget v0, Lorg/telegram/messenger/R$string;->FoldersInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 190
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->APP_NAVIGATION_SETTINGS:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->AppNavigation:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_newphone:I

    sget v0, Lorg/telegram/messenger/R$string;->AppNavigationInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    const/16 v12, 0x768

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v10, 0x0

    const-string v8, "appNavigationSettings"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 191
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->APP_ICON:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->AppIcon:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->menu_edit_appearance:I

    invoke-static {}, Lcom/exteragram/messenger/appicons/AppIconController;->getSelectedIcon()Lcom/exteragram/messenger/appicons/AppIcon;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/appicons/AppIcon;->getTitle()Ljava/lang/CharSequence;

    move-result-object v5

    const-string v8, "appIcon"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 192
    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda20;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda20;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/UItem;->onBind(Lorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    .line 191
    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 201
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->ICON_PACKS:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->IconPacks:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_sticker:I

    sget v4, Lorg/telegram/messenger/R$string;->IconPacksInfo:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    const/4 v4, 0x0

    const-string v8, "iconPacks"

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 202
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->PILL_STACK:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->PillStackPills:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->outline_header_search:I

    sget v0, Lorg/telegram/messenger/R$string;->PillStackPillsInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v5

    const-string v8, "pillStack"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 203
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 205
    sget v1, Lorg/telegram/messenger/R$string;->Appearance:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 206
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->FAB_SHAPE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->getId()I

    move-result v1

    iget-object v2, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->fabShapeCell:Lcom/exteragram/messenger/preferences/appearance/components/FabShapeCell;

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    const-string v2, "fabShape"

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 207
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->USE_SYSTEM_FONTS:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->UseSystemFonts:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getUseSystemFonts()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v8, 0x0

    const-string v7, "useSystemFonts"

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 208
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->USE_SYSTEM_EMOJI:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->UseSystemEmoji:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget-boolean v3, Lorg/telegram/messenger/SharedConfig;->useSystemEmoji:Z

    const-string v7, "useSystemEmoji"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 209
    iget-object v1, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->md3Styles:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {v1, v14}, Lcom/exteragram/messenger/preferences/SwitchGroup;->fill(Ljava/util/ArrayList;)V

    .line 210
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->GOOEY_AVATAR_ANIMATION:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->GooeyAvatarAnimation:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGooeyAvatarAnimation()Z

    move-result v3

    const-string v7, "gooeyAvatarAnimation"

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 211
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->CUSTOM_THEMES:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->CustomChatThemes:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getCustomThemes()Z

    move-result v3

    const-string v7, "customThemes"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 212
    sget v1, Lorg/telegram/messenger/R$string;->CustomChatThemesInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 214
    const-string v1, "Appearance-Sections"

    invoke-static {v1}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->markAsNewFeature(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_3

    sget v1, Lorg/telegram/messenger/R$string;->Sections:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->applyNewSpan(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v1

    goto :goto_0

    :cond_3
    sget v1, Lorg/telegram/messenger/R$string;->Sections:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    :goto_0
    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 215
    invoke-direct {v0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->createSectionRadiusSliderItem()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v1, v0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    const-string v2, "sectionRadius"

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 216
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDividerStyle()Lcom/exteragram/messenger/DividerStyle;

    move-result-object v1

    sget-object v2, Lcom/exteragram/messenger/DividerStyle;->SEGMENTS:Lcom/exteragram/messenger/DividerStyle;

    const/4 v12, 0x1

    if-ne v1, v2, :cond_4

    move v13, v12

    goto :goto_1

    :cond_4
    const/4 v1, 0x0

    move v13, v1

    .line 217
    :goto_1
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->SEPARATED_HEADERS:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->SeparateHeaders:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getSectionsSeparatedHeaders()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v11, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "sectionsSeparatedHeaders"

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    xor-int/lit8 v2, v13, 0x1

    .line 218
    invoke-virtual {v1, v2}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    .line 219
    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 220
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->DIVIDER_STYLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->DividerStyle:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->dividerStyles:[Ljava/lang/CharSequence;

    if-nez v3, :cond_5

    move-object v3, v15

    :cond_5
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDividerStyle()Lcom/exteragram/messenger/DividerStyle;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/Enum;->ordinal()I

    move-result v4

    aget-object v4, v3, v4

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "dividerStyle"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 221
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 223
    sget v1, Lorg/telegram/messenger/R$string;->BlurOptions:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 224
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->GLASS_OUTLINE_STYLE:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v2, Lorg/telegram/messenger/R$string;->GlassOutlineStyle:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->glassOutlineStyles:[Ljava/lang/CharSequence;

    if-nez v3, :cond_6

    goto :goto_2

    :cond_6
    move-object v15, v3

    :goto_2
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGlassOutlineStyle()Lcom/exteragram/messenger/GlassOutlineStyle;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Enum;->ordinal()I

    move-result v3

    aget-object v4, v15, v3

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "glassOutlineStyle"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 225
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->GLASS_MESSAGE_MENU:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->GlassMessageMenu:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGlassMessageMenu()Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->GlassMessageMenuInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1b0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "glassMessageMenu"

    const/4 v8, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 226
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->FORCE_BLUR:Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    sget v0, Lorg/telegram/messenger/R$string;->ForceBlur:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getForceBlur()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const-string v7, "forceBlur"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 227
    sget v0, Lorg/telegram/messenger/R$string;->ForceBlurInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 164
    sget p0, Lorg/telegram/messenger/R$string;->Appearance:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public initializeOptionStrings()V
    .locals 8

    .line 127
    sget v0, Lorg/telegram/messenger/R$string;->exteraAppName:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 128
    sget v1, Lorg/telegram/messenger/R$string;->ActionBarTitleUsername:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 129
    sget v2, Lorg/telegram/messenger/R$string;->ActionBarTitleName:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 130
    sget v3, Lorg/telegram/messenger/R$string;->FilterChats:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x4

    new-array v4, v4, [Ljava/lang/CharSequence;

    const/4 v5, 0x0

    aput-object v0, v4, v5

    const/4 v0, 0x1

    aput-object v1, v4, v0

    const/4 v1, 0x2

    aput-object v2, v4, v1

    const/4 v2, 0x3

    aput-object v3, v4, v2

    .line 126
    iput-object v4, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->titles:[Ljava/lang/CharSequence;

    .line 133
    sget v3, Lorg/telegram/messenger/R$string;->TabTitleStyleTextWithIcons:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 134
    sget v4, Lorg/telegram/messenger/R$string;->TabTitleStyleTextOnly:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 135
    sget v6, Lorg/telegram/messenger/R$string;->TabTitleStyleIconsOnly:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-array v7, v2, [Ljava/lang/CharSequence;

    aput-object v3, v7, v5

    aput-object v4, v7, v0

    aput-object v6, v7, v1

    .line 132
    iput-object v7, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->tabIconsOptions:[Ljava/lang/CharSequence;

    .line 138
    sget v3, Lorg/telegram/messenger/R$string;->FilterAllChats:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 139
    sget v4, Lorg/telegram/messenger/R$string;->TabCounterUnmuted:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 140
    sget v6, Lorg/telegram/messenger/R$string;->BlurOff:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-array v7, v2, [Ljava/lang/CharSequence;

    aput-object v3, v7, v5

    aput-object v4, v7, v0

    aput-object v6, v7, v1

    .line 137
    iput-object v7, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->tabCounterModes:[Ljava/lang/CharSequence;

    .line 143
    sget v3, Lorg/telegram/messenger/R$string;->DividerStyleHidden:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 144
    sget v4, Lorg/telegram/messenger/R$string;->DividerStyleLine:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 145
    sget v6, Lorg/telegram/messenger/R$string;->DividerStyleSegments:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-array v7, v2, [Ljava/lang/CharSequence;

    aput-object v3, v7, v5

    aput-object v4, v7, v0

    aput-object v6, v7, v1

    .line 142
    iput-object v7, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->dividerStyles:[Ljava/lang/CharSequence;

    .line 148
    sget v3, Lorg/telegram/messenger/R$string;->GlassOutlineGlare:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 149
    sget v4, Lorg/telegram/messenger/R$string;->GlassOutlineSolid:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 150
    sget v6, Lorg/telegram/messenger/R$string;->GlassOutlineHidden:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-array v2, v2, [Ljava/lang/CharSequence;

    aput-object v3, v2, v5

    aput-object v4, v2, v0

    aput-object v6, v2, v1

    .line 147
    iput-object v2, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->glassOutlineStyles:[Ljava/lang/CharSequence;

    return-void
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 6

    .line 231
    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez p2, :cond_9

    invoke-static {}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p3

    invoke-interface {p3}, Ljava/util/List;->size()I

    move-result p3

    if-le p2, p3, :cond_0

    goto/16 :goto_1

    .line 232
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p2

    iget p3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 p4, 0x1

    sub-int/2addr p3, p4

    invoke-interface {p2, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$AppearanceItem;

    .line 233
    sget-object p3, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    aget p2, p3, p2

    const/4 p3, 0x0

    const/4 p5, 0x0

    packed-switch p2, :pswitch_data_0

    goto/16 :goto_1

    .line 320
    :pswitch_0
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda10;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda10;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 321
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleGlassMessageMenuChange()V

    return-void

    .line 316
    :pswitch_1
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda9;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda9;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 317
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleForceBlurChange()V

    return-void

    .line 311
    :pswitch_2
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->glassOutlineStyles:[Ljava/lang/CharSequence;

    if-nez p2, :cond_1

    move-object v2, p3

    goto :goto_0

    :cond_1
    move-object v2, p2

    :goto_0
    sget p2, Lorg/telegram/messenger/R$string;->GlassOutlineStyle:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getGlassOutlineStyle()Lcom/exteragram/messenger/GlassOutlineStyle;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result v4

    new-instance v5, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda8;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda8;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    move-object v0, p0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 307
    :pswitch_3
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda7;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda7;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 308
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->chatListPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;

    if-eqz p0, :cond_9

    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;->invalidate()V

    return-void

    .line 305
    :pswitch_4
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda6;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda6;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 304
    :pswitch_5
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda5;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda5;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 303
    :pswitch_6
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->md3Styles:Lcom/exteragram/messenger/preferences/SwitchGroup;

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/preferences/SwitchGroup;->onClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 301
    :pswitch_7
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleUseSystemEmojiClick(Lorg/telegram/ui/Components/UItem;)V

    return-void

    .line 298
    :pswitch_8
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda4;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda4;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 299
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleUseSystemFontsClick()V

    return-void

    .line 292
    :pswitch_9
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->dividerStyles:[Ljava/lang/CharSequence;

    if-nez p2, :cond_2

    move-object p2, p3

    :cond_2
    sget p3, Lorg/telegram/messenger/R$string;->DividerStyle:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDividerStyle()Lcom/exteragram/messenger/DividerStyle;

    move-result-object p4

    invoke-virtual {p4}, Ljava/lang/Enum;->ordinal()I

    move-result p4

    new-instance p5, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda3;

    invoke-direct {p5, p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 287
    :pswitch_a
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDividerStyle()Lcom/exteragram/messenger/DividerStyle;

    move-result-object p2

    sget-object p3, Lcom/exteragram/messenger/DividerStyle;->SEGMENTS:Lcom/exteragram/messenger/DividerStyle;

    if-ne p2, p3, :cond_3

    goto/16 :goto_1

    .line 288
    :cond_3
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda2;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda2;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 289
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    invoke-virtual {p0}, Landroidx/recyclerview/widget/RecyclerView;->invalidateItemDecorations()V

    return-void

    .line 285
    :pswitch_b
    new-instance p1, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/icons/ui/IconPacksActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 284
    :pswitch_c
    new-instance p1, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/appicons/ui/AppIconsActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 283
    :pswitch_d
    new-instance p1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 280
    :pswitch_e
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda1;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda1;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 281
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->handleHideAllChatsClick()V

    return-void

    .line 275
    :pswitch_f
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->tabCounterModes:[Ljava/lang/CharSequence;

    if-nez p2, :cond_4

    move-object p2, p3

    :cond_4
    sget p3, Lorg/telegram/messenger/R$string;->TabCounter:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTabCounterMode()Lcom/exteragram/messenger/TabCounterMode;

    move-result-object p4

    invoke-virtual {p4}, Ljava/lang/Enum;->ordinal()I

    move-result p4

    new-instance p5, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda19;

    invoke-direct {p5, p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda19;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 270
    :pswitch_10
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->tabIconsOptions:[Ljava/lang/CharSequence;

    if-nez p2, :cond_5

    move-object p2, p3

    :cond_5
    sget p3, Lorg/telegram/messenger/R$string;->TabTitleStyle:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-static {}, Lcom/exteragram/messenger/TabIconsMode;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p4

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTabIcons()Lcom/exteragram/messenger/TabIconsMode;

    move-result-object p5

    invoke-interface {p4, p5}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result p4

    new-instance p5, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda18;

    invoke-direct {p5, p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda18;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 268
    :pswitch_11
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda17;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda17;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 264
    :pswitch_12
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda16;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda16;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 265
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, p5}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void

    .line 260
    :pswitch_13
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda15;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda15;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 261
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, p5}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void

    .line 254
    :pswitch_14
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda14;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda14;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 255
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->chatListPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;

    if-eqz p1, :cond_6

    invoke-virtual {p1, p4}, Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;->updateCentered(Z)V

    .line 256
    :cond_6
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p1, p4}, Lorg/telegram/ui/ActionBar/ActionBar;->refreshTitlePosition(Z)V

    .line 257
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, p5}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void

    .line 249
    :pswitch_15
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda13;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda13;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 250
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->chatListPreviewCell:Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;

    if-eqz p1, :cond_7

    invoke-virtual {p1, p4}, Lcom/exteragram/messenger/preferences/appearance/components/ChatListPreviewCell;->updateStatus(Z)V

    .line 251
    :cond_7
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, p5}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void

    .line 245
    :pswitch_16
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda12;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda12;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 246
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/NotificationCenter;->storiesEnabledUpdate:I

    new-array p2, p5, [Ljava/lang/Object;

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void

    .line 243
    :pswitch_17
    new-instance p1, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/pillstack/ui/PillStackPreferencesActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 239
    :pswitch_18
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;->titles:[Ljava/lang/CharSequence;

    if-nez p2, :cond_8

    move-object p2, p3

    :cond_8
    sget p3, Lorg/telegram/messenger/R$string;->ActionBarTitle:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTitleText()I

    move-result p4

    new-instance p5, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda11;

    invoke-direct {p5, p0}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda11;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity;)V

    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 235
    :pswitch_19
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda0;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppearancePreferencesActivity$$ExternalSyntheticLambda0;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 236
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, p5}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    :cond_9
    :goto_1
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
