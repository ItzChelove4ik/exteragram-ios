.class public final Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;,
        Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$Companion;,
        Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;,
        Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\u0008\u0004\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0012\n\u0002\u0018\u0002\n\u0002\u0008\u0007\u0018\u0000 @2\u00020\u0001:\u0003?@AB\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u0017\u001a\u00020\u0018H\u0014J\u0008\u0010\u0019\u001a\u00020\u0018H\u0002J\u0008\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J\u001e\u0010$\u001a\u00020\u00182\u000c\u0010%\u001a\u0008\u0012\u0004\u0012\u00020&0\u000f2\u0006\u0010\'\u001a\u00020(H\u0014J<\u0010)\u001a\u00020\u00182\u000c\u0010%\u001a\u0008\u0012\u0004\u0012\u00020&0\u000f2\u0006\u0010\'\u001a\u00020(2\u0006\u0010*\u001a\u00020\u001b2\u000c\u0010+\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010,\u001a\u00020!H\u0002J\u001c\u0010-\u001a\u0004\u0018\u00010&2\u0006\u0010.\u001a\u00020\u00062\u0008\u0010/\u001a\u0004\u0018\u00010\u0007H\u0002J\u001e\u00100\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u00062\u000c\u00102\u001a\u0008\u0012\u0004\u0012\u00020&0\u000fH\u0002J0\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020&2\u0006\u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u00020#2\u0006\u00108\u001a\u00020#H\u0014J\u0008\u00109\u001a\u00020\u0018H\u0002J\u0008\u0010:\u001a\u00020;H\u0002J\u0012\u0010<\u001a\u00020\u00182\u0008\u0008\u0002\u0010=\u001a\u00020!H\u0002J\u0008\u0010>\u001a\u00020\u0018H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0008\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000c\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\u0008\u0012\u0004\u0012\u00020\u00130\u0012X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0014R\u0016\u0010\u0015\u001a\u0008\u0012\u0004\u0012\u00020\u00130\u0012X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0014R\u0016\u0010\u0016\u001a\u0008\u0012\u0004\u0012\u00020\u00130\u0012X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0014\u00a8\u0006B"
    }
    d2 = {
        "Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "itemDetails",
        "Ljava/util/HashMap;",
        "",
        "Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;",
        "reorderIcon",
        "Landroid/graphics/drawable/Drawable;",
        "resetItem",
        "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;",
        "predictiveBackSeekbar",
        "Lcom/exteragram/messenger/preferences/components/AltSeekbar;",
        "stableDividerIds",
        "Ljava/util/ArrayList;",
        "nextDividerId",
        "tabletModeOptions",
        "",
        "",
        "[Ljava/lang/CharSequence;",
        "bottomNavigationModes",
        "transitionAnimations",
        "initializeOptionStrings",
        "",
        "initItemDetails",
        "getTitle",
        "",
        "createView",
        "Landroid/view/View;",
        "context",
        "Landroid/content/Context;",
        "isPredictiveBackOff",
        "",
        "value",
        "",
        "fillItems",
        "items",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "addMenuSection",
        "header",
        "ids",
        "mainSection",
        "createMenuItem",
        "id",
        "info",
        "updateConfigFromReorder",
        "sectionId",
        "uItems",
        "onClick",
        "item",
        "view",
        "position",
        "x",
        "y",
        "saveAndNotify",
        "refreshEditorList",
        "Lorg/telegram/ui/Components/UniversalRecyclerView;",
        "updateResetButtonVisibility",
        "animated",
        "resetToDefault",
        "AppNavigationItem",
        "Companion",
        "ItemInfo",
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

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nAppNavigationPreferencesActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppNavigationPreferencesActivity.kt\ncom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,425:1\n1#2:426\n41#3,12:427\n41#3,12:439\n*S KotlinDebug\n*F\n+ 1 AppNavigationPreferencesActivity.kt\ncom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity\n*L\n297#1:427,12\n309#1:439,12\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$Companion;


# instance fields
.field private bottomNavigationModes:[Ljava/lang/CharSequence;

.field private final itemDetails:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;",
            ">;"
        }
    .end annotation
.end field

.field private nextDividerId:I

.field private predictiveBackSeekbar:Lcom/exteragram/messenger/preferences/components/AltSeekbar;

.field private reorderIcon:Landroid/graphics/drawable/Drawable;

.field private resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

.field private final stableDividerIds:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private tabletModeOptions:[Ljava/lang/CharSequence;

.field private transitionAnimations:[Ljava/lang/CharSequence;


# direct methods
.method public static synthetic $r8$lambda$7__uhjIw6L6gMmRp354aKUrT6go(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;ILjava/util/ArrayList;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->updateConfigFromReorder(ILjava/util/ArrayList;)V

    return-void
.end method

.method public static $r8$lambda$7b-srUJMVDg8LOhej8PgcZ3VlxY(Z)V
    .locals 0

    .line 294
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setImmersiveDrawerAnimation(Z)V

    return-void
.end method

.method public static $r8$lambda$7iNv2_6uhH1q004nrPLZHfLpo1Q(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Z
    .locals 0

    .line 122
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$9W76nbjQWdGOuv7NzhtLBfUwV48(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;F)V
    .locals 3

    .line 140
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPredictiveBackIntensity()F

    move-result v0

    invoke-direct {p0, v0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->isPredictiveBackOff(F)Z

    move-result v0

    .line 141
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->isPredictiveBackOff(F)Z

    move-result v1

    .line 142
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setPredictiveBackIntensity(F)V

    .line 143
    iget-object v2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->predictiveBackSeekbar:Lcom/exteragram/messenger/preferences/components/AltSeekbar;

    if-eqz v2, :cond_0

    invoke-virtual {v2, p1}, Lcom/exteragram/messenger/preferences/components/AltSeekbar;->updateHeader(F)V

    :cond_0
    if-eq v0, v1, :cond_1

    .line 144
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showRestartBulletin()V

    :cond_1
    return-void
.end method

.method public static $r8$lambda$FKdqUjxjgDeAzPXGJsdoCPgJ80I(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;I)V
    .locals 0

    .line 317
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setTabletMode(I)V

    .line 318
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showRestartBulletin()V

    return-void
.end method

.method public static $r8$lambda$OcnwmapLcYeDEa0ramhMi8X2sWc(I)V
    .locals 2

    .line 307
    invoke-static {}, Lcom/exteragram/messenger/TransitionAnimation;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/TransitionAnimation;

    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setTransitionAnimation(Lcom/exteragram/messenger/TransitionAnimation;)V

    .line 308
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTransitionAnimation()Lcom/exteragram/messenger/TransitionAnimation;

    move-result-object p0

    sget-object v0, Lcom/exteragram/messenger/TransitionAnimation;->DEFAULT:Lcom/exteragram/messenger/TransitionAnimation;

    if-eq p0, v0, :cond_0

    .line 309
    invoke-static {}, Lorg/telegram/messenger/MessagesController;->getGlobalMainSettings()Landroid/content/SharedPreferences;

    move-result-object p0

    .line 45
    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    .line 310
    const-string v0, "view_animations"

    const/4 v1, 0x1

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 312
    invoke-static {v1}, Lorg/telegram/messenger/SharedConfig;->setAnimationsEnabled(Z)V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$abRkaPj59RSDKtqDhmMPduco8WY(Ljava/lang/Integer;)Z
    .locals 1

    .line 122
    sget-object v0, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v0

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static $r8$lambda$wUuxrEg0ymhtYK31jN2UMNG3nBo(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;I)V
    .locals 3

    .line 297
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    .line 45
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 297
    const-string v1, "bottomNavigationBarMode"

    invoke-interface {v0, v1, p1}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 298
    invoke-static {p1}, Lcom/exteragram/messenger/config/BottomNavigationBar;->setMode(I)V

    .line 299
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->ensureSettingsVisibility()V

    .line 300
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget v0, Lorg/telegram/messenger/NotificationCenter;->mainUserInfoChanged:I

    const/4 v1, 0x0

    new-array v2, v1, [Ljava/lang/Object;

    invoke-virtual {p1, v0, v2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 301
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->refreshEditorList()Lorg/telegram/ui/Components/UniversalRecyclerView;

    const/4 p1, 0x1

    const/4 v0, 0x0

    .line 302
    invoke-static {p0, v1, p1, v0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->updateResetButtonVisibility$default(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;ZILjava/lang/Object;)V

    .line 303
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, v1}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void
.end method

.method public static $r8$lambda$x0uY8g49vyT3wlPwQldxwo343Xc(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;Landroid/view/View;)V
    .locals 0

    .line 171
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->resetToDefault()V

    return-void
.end method

.method public static $r8$lambda$zbZoGl8HqFPZHMddPi5kgoHhzqM(Z)V
    .locals 0

    .line 288
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setNavigationDrawer(Z)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->Companion:Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$Companion;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 41
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    .line 43
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    .line 71
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    const/16 v0, -0x7d0

    .line 72
    iput v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    return-void
.end method

.method private final addMenuSection(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;Ljava/lang/String;Ljava/util/ArrayList;Z)V
    .locals 15
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;",
            "Lorg/telegram/ui/Components/UniversalAdapter;",
            "Ljava/lang/String;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Integer;",
            ">;Z)V"
        }
    .end annotation

    move-object/from16 v14, p1

    .line 212
    invoke-virtual/range {p2 .. p2}, Lorg/telegram/ui/Components/UniversalAdapter;->whiteSectionStart()V

    .line 213
    invoke-static/range {p3 .. p3}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 214
    invoke-virtual/range {p2 .. p2}, Lorg/telegram/ui/Components/UniversalAdapter;->reorderSectionStart()I

    .line 217
    invoke-virtual/range {p4 .. p4}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    const/4 v2, 0x0

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_5

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/Number;

    invoke-virtual {v3}, Ljava/lang/Number;->intValue()I

    move-result v3

    .line 218
    sget-object v4, Lcom/exteragram/messenger/MainMenuItem;->PLUGINS:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v4}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v4

    if-ne v3, v4, :cond_1

    sget-object v4, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineSupported()Z

    move-result v4

    if-nez v4, :cond_1

    goto :goto_0

    .line 221
    :cond_1
    sget-object v4, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v4}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v5

    if-ne v3, v5, :cond_4

    const/4 v3, 0x0

    if-eqz p5, :cond_2

    .line 222
    iget-object v5, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v5

    if-ge v2, v5, :cond_2

    .line 223
    iget-object v4, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    invoke-virtual {v4, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Number;

    invoke-virtual {v4}, Ljava/lang/Number;->intValue()I

    move-result v4

    invoke-direct {p0, v4, v3}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->createMenuItem(ILcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;)Lorg/telegram/ui/Components/UItem;

    move-result-object v3

    if-eqz v3, :cond_3

    invoke-virtual {v14, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_2
    if-nez p5, :cond_3

    .line 225
    invoke-virtual {v4}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v4

    invoke-direct {p0, v4, v3}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->createMenuItem(ILcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;)Lorg/telegram/ui/Components/UItem;

    move-result-object v3

    if-eqz v3, :cond_3

    invoke-virtual {v14, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_3
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 228
    :cond_4
    iget-object v4, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    if-eqz v4, :cond_0

    .line 229
    invoke-direct {p0, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->createMenuItem(ILcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;)Lorg/telegram/ui/Components/UItem;

    move-result-object v3

    if-eqz v3, :cond_0

    invoke-virtual {v14, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 234
    :cond_5
    invoke-virtual/range {p2 .. p2}, Lorg/telegram/ui/Components/UniversalAdapter;->reorderSectionEnd()V

    if-eqz p5, :cond_6

    .line 236
    sget v1, Lorg/telegram/messenger/R$string;->MainMenuAddDivider:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_add:I

    const/16 v12, 0x5b8

    const/4 v13, 0x0

    const/16 v1, -0xc8

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x1

    const/4 v11, 0x0

    move-object v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 237
    :cond_6
    invoke-virtual/range {p2 .. p2}, Lorg/telegram/ui/Components/UniversalAdapter;->whiteSectionEnd()V

    return-void
.end method

.method private final createMenuItem(ILcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;)Lorg/telegram/ui/Components/UItem;
    .locals 15

    move/from16 v1, p1

    const/16 v0, -0x7d0

    const/4 v14, 0x0

    if-le v1, v0, :cond_2

    .line 241
    sget-object v0, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v0

    if-ne v1, v0, :cond_0

    goto :goto_0

    :cond_0
    if-eqz p2, :cond_1

    .line 242
    invoke-virtual/range {p2 .. p2}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->getName()Ljava/lang/CharSequence;

    move-result-object v2

    invoke-virtual/range {p2 .. p2}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;->getIconRes()I

    move-result v3

    const/16 v12, 0x7b8

    const/4 v13, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    move-object v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    goto :goto_1

    :cond_1
    move-object v1, v14

    goto :goto_1

    .line 241
    :cond_2
    :goto_0
    sget v0, Lorg/telegram/messenger/R$string;->MainMenuDivider:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_block:I

    const/16 v12, 0x7b8

    const/4 v13, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    move-object v0, p0

    move/from16 v1, p1

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    :goto_1
    if-eqz v1, :cond_3

    .line 244
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->reorderIcon:Landroid/graphics/drawable/Drawable;

    iput-object p0, v1, Lorg/telegram/ui/Components/UItem;->object2:Ljava/lang/Object;

    return-object v1

    :cond_3
    return-object v14
.end method

.method private final initItemDetails()V
    .locals 5

    .line 104
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->PROFILE:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->MyProfile:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->left_status_profile:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 105
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->ARCHIVE:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->ArchivedChats:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_archive:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 107
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->BOTS:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->FilterBots:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_bot:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 109
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->NEW_GROUP:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->NewGroup:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_groups:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 110
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->CONTACTS:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->Contacts:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_contacts:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 111
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->NEW_CHANNEL:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->NewChannel:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_channel:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 112
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->CALLS:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->Calls:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_calls:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 113
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->SAVED:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->SavedMessages:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_saved:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 114
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->FEED:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->Feed:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->ic_feed:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 116
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->SETTINGS:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->Settings:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_settings_old:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 118
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->PLUGINS:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->Plugins:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_plugins:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 119
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->BROWSER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->BrowserSettingsTitle:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg2_language:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 120
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->itemDetails:Ljava/util/HashMap;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->QR:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;

    sget v3, Lorg/telegram/messenger/R$string;->AuthAnotherClient:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    sget v4, Lorg/telegram/messenger/R$drawable;->msg_qrcode:I

    invoke-direct {v2, v3, v4}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$ItemInfo;-><init>(Ljava/lang/CharSequence;I)V

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 122
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda0;

    invoke-direct {v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda0;-><init>()V

    new-instance v2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda1;

    invoke-direct {v2, v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda1;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->removeIf(Ljava/util/function/Predicate;)Z

    .line 124
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    const/16 v0, -0x7d0

    .line 125
    iput v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    .line 126
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Number;

    invoke-virtual {v1}, Ljava/lang/Number;->intValue()I

    move-result v1

    .line 127
    sget-object v2, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v2}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v2

    if-ne v1, v2, :cond_0

    .line 128
    iget-object v1, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    iget v2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    add-int/lit8 v3, v2, -0x1

    iput v3, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_1
    return-void
.end method

.method private final isPredictiveBackOff(F)Z
    .locals 0

    const/high16 p0, 0x41200000    # 10.0f

    mul-float/2addr p1, p0

    .line 182
    invoke-static {p1}, Lkotlin/math/MathKt;->roundToInt(F)I

    move-result p1

    int-to-float p1, p1

    div-float/2addr p1, p0

    const/4 p0, 0x0

    cmpg-float p0, p1, p0

    if-gtz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private final refreshEditorList()Lorg/telegram/ui/Components/UniversalRecyclerView;
    .locals 2

    .line 388
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    const/4 v0, 0x0

    .line 389
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/RecyclerListView;->hideSelector(Z)V

    .line 390
    invoke-virtual {p0, v0}, Lorg/telegram/ui/Components/RecyclerListView;->cancelClickRunnables(Z)V

    .line 391
    iget-object v0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    return-object p0
.end method

.method private final resetToDefault()V
    .locals 4

    .line 401
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 402
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDefaultMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 404
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    .line 405
    invoke-static {}, Lcom/exteragram/messenger/MainMenuItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/MainMenuItem;

    .line 406
    sget-object v2, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    if-eq v1, v2, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 407
    sget-object v2, Lcom/exteragram/messenger/MainMenuItem;->PLUGINS:Lcom/exteragram/messenger/MainMenuItem;

    if-ne v1, v2, :cond_1

    sget-object v2, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineSupported()Z

    move-result v2

    if-nez v2, :cond_1

    goto :goto_0

    .line 409
    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object v2

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 413
    :cond_2
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    const/16 v0, -0x7d0

    .line 414
    iput v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    .line 415
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_3
    :goto_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Number;

    invoke-virtual {v1}, Ljava/lang/Number;->intValue()I

    move-result v1

    .line 416
    sget-object v2, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v2}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v2

    if-ne v1, v2, :cond_3

    .line 417
    iget-object v1, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    iget v2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    add-int/lit8 v3, v2, -0x1

    iput v3, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 419
    :cond_4
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->saveMainMenuLayout()V

    .line 420
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->mainUserInfoChanged:I

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v3}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 422
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->refreshEditorList()Lorg/telegram/ui/Components/UniversalRecyclerView;

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 423
    invoke-static {p0, v2, v0, v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->updateResetButtonVisibility$default(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;ZILjava/lang/Object;)V

    return-void
.end method

.method private final saveAndNotify()V
    .locals 4

    .line 382
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->saveMainMenuLayout()V

    .line 383
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/NotificationCenter;->mainUserInfoChanged:I

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v3}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 384
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->refreshEditorList()Lorg/telegram/ui/Components/UniversalRecyclerView;

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 385
    invoke-static {p0, v2, v0, v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->updateResetButtonVisibility$default(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;ZILjava/lang/Object;)V

    return-void
.end method

.method private final updateConfigFromReorder(ILjava/util/ArrayList;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/ui/Components/UItem;",
            ">;)V"
        }
    .end annotation

    .line 247
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 248
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 250
    invoke-virtual {p2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :cond_0
    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/ui/Components/UItem;

    .line 251
    iget v3, v2, Lorg/telegram/ui/Components/UItem;->id:I

    const/16 v4, -0x7d0

    if-le v3, v4, :cond_2

    sget-object v5, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v5}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v5

    if-ne v3, v5, :cond_1

    goto :goto_1

    .line 258
    :cond_1
    iget v2, v2, Lorg/telegram/ui/Components/UItem;->id:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_2
    :goto_1
    if-nez p1, :cond_0

    .line 254
    sget-object v3, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v3}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 255
    iget v2, v2, Lorg/telegram/ui/Components/UItem;->id:I

    if-gt v2, v4, :cond_3

    goto :goto_2

    :cond_3
    iget v2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    add-int/lit8 v3, v2, -0x1

    iput v3, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    :goto_2
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_4
    if-eqz p1, :cond_7

    const/4 p2, 0x1

    if-eq p1, p2, :cond_5

    goto :goto_3

    .line 273
    :cond_5
    invoke-static {}, Lcom/exteragram/messenger/config/BottomNavigationBar;->hidden()Z

    move-result p1

    if-eqz p1, :cond_6

    sget-object p1, Lcom/exteragram/messenger/MainMenuItem;->SETTINGS:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {p1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result p1

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 274
    :cond_6
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/ArrayList;->clear()V

    .line 275
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    goto :goto_3

    .line 263
    :cond_7
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->clear()V

    .line 264
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 265
    invoke-static {}, Lcom/exteragram/messenger/config/BottomNavigationBar;->hidden()Z

    move-result p1

    if-eqz p1, :cond_8

    .line 266
    sget-object p1, Lcom/exteragram/messenger/MainMenuItem;->SETTINGS:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {p1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result p1

    .line 267
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_8

    .line 268
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 270
    :cond_8
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object p1

    invoke-virtual {p1}, Ljava/util/ArrayList;->clear()V

    .line 271
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object p1

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    .line 278
    :goto_3
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->saveAndNotify()V

    return-void
.end method

.method private final updateResetButtonVisibility(Z)V
    .locals 2

    .line 395
    iget-object v0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    if-nez v0, :cond_0

    return-void

    .line 396
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getDefaultMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v1

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    xor-int/lit8 v0, v0, 0x1

    .line 397
    iget-object p0, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    const/high16 v1, 0x3f000000    # 0.5f

    invoke-static {p0, v0, v1, p1}, Lorg/telegram/messenger/AndroidUtilities;->updateViewVisibilityAnimated(Landroid/view/View;ZFZ)V

    return-void
.end method

.method public static synthetic updateResetButtonVisibility$default(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;ZILjava/lang/Object;)V
    .locals 0

    const/4 p3, 0x1

    and-int/2addr p2, p3

    if-eqz p2, :cond_0

    move p1, p3

    .line 394
    :cond_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->updateResetButtonVisibility(Z)V

    return-void
.end method


# virtual methods
.method public createView(Landroid/content/Context;)Landroid/view/View;
    .locals 9

    .line 134
    invoke-super {p0, p1}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->createView(Landroid/content/Context;)Landroid/view/View;

    move-result-object v0

    .line 136
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x22

    if-lt v1, v2, :cond_1

    .line 137
    new-instance v5, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda2;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;)V

    .line 147
    sget v1, Lorg/telegram/messenger/R$string;->PredictiveBackIntensity:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    .line 148
    sget v1, Lorg/telegram/messenger/R$string;->BlurOff:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v7

    sget v1, Lorg/telegram/messenger/R$string;->PredictiveBackMax:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v8

    .line 137
    new-instance v3, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$createView$seekbar$1;

    move-object v4, p1

    invoke-direct/range {v3 .. v8}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$createView$seekbar$1;-><init>(Landroid/content/Context;Lcom/exteragram/messenger/preferences/components/AltSeekbar$OnDrag;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    .line 162
    iput-object v3, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->predictiveBackSeekbar:Lcom/exteragram/messenger/preferences/components/AltSeekbar;

    .line 163
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPredictiveBackIntensity()F

    move-result p1

    const/high16 v1, 0x40000000    # 2.0f

    invoke-static {p1, v1}, Lkotlin/ranges/RangesKt;->coerceAtMost(FF)F

    move-result p1

    .line 164
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPredictiveBackIntensity()F

    move-result v1

    cmpg-float v1, p1, v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 165
    :cond_0
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setPredictiveBackIntensity(F)V

    .line 166
    :goto_0
    invoke-virtual {v3, p1}, Lcom/exteragram/messenger/preferences/components/AltSeekbar;->setProgress(F)V

    .line 169
    :cond_1
    iget-object p1, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->actionBar:Lorg/telegram/ui/ActionBar/ActionBar;

    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/ActionBar;->createMenu()Lorg/telegram/ui/ActionBar/ActionBarMenu;

    move-result-object p1

    sget v1, Lorg/telegram/messenger/R$drawable;->msg_reset:I

    const/4 v2, 0x0

    invoke-virtual {p1, v2, v1}, Lorg/telegram/ui/ActionBar/ActionBarMenu;->addItem(II)Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    move-result-object p1

    .line 170
    sget v1, Lorg/telegram/messenger/R$string;->Reset:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v1}, Landroid/view/View;->setContentDescription(Ljava/lang/CharSequence;)V

    .line 171
    new-instance v1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda3;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;)V

    invoke-virtual {p1, v1}, Lorg/telegram/ui/ActionBar/ActionBarMenuItem;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 169
    iput-object p1, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->resetItem:Lorg/telegram/ui/ActionBar/ActionBarMenuItem;

    .line 174
    invoke-direct {p0, v2}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->updateResetButtonVisibility(Z)V

    .line 176
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    const/4 v1, 0x1

    invoke-virtual {p1, v1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->allowReorder(Z)V

    .line 177
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    new-instance v1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;)V

    invoke-virtual {p1, v1}, Lorg/telegram/ui/Components/UniversalRecyclerView;->listenReorder(Lorg/telegram/messenger/Utilities$Callback2;)V

    return-object v0
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 17
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

    .line 185
    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v2, 0x22

    if-lt v1, v2, :cond_0

    iget-object v1, v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->predictiveBackSeekbar:Lcom/exteragram/messenger/preferences/components/AltSeekbar;

    if-eqz v1, :cond_0

    const/4 v1, 0x1

    :goto_0
    move v15, v1

    goto :goto_1

    :cond_0
    const/4 v1, 0x0

    goto :goto_0

    .line 186
    :goto_1
    iget-object v1, v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->reorderIcon:Landroid/graphics/drawable/Drawable;

    if-nez v1, :cond_1

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getContext()Landroid/content/Context;

    move-result-object v1

    sget v2, Lorg/telegram/messenger/R$drawable;->list_reorder:I

    invoke-static {v1, v2}, Landroidx/core/content/ContextCompat;->getDrawable(Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;

    move-result-object v1

    :cond_1
    iput-object v1, v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->reorderIcon:Landroid/graphics/drawable/Drawable;

    .line 188
    sget v1, Lorg/telegram/messenger/R$string;->General:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 189
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->TABLET_MODE:Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->getId()I

    move-result v1

    sget v2, Lorg/telegram/messenger/R$string;->TabletMode:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->tabletModeOptions:[Ljava/lang/CharSequence;

    const/16 v16, 0x0

    if-nez v3, :cond_2

    move-object/from16 v3, v16

    :cond_2
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTabletMode()I

    move-result v4

    aget-object v4, v3, v4

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "tabletMode"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 190
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->BOTTOM_NAVIGATION_BAR_MODE:Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->getId()I

    move-result v1

    sget v2, Lorg/telegram/messenger/R$string;->BottomNavigationBarMode:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->bottomNavigationModes:[Ljava/lang/CharSequence;

    if-nez v3, :cond_3

    move-object/from16 v3, v16

    :cond_3
    invoke-static {}, Lcom/exteragram/messenger/config/BottomNavigationBar;->getMode()I

    move-result v4

    aget-object v4, v3, v4

    const/16 v12, 0x774

    const/4 v13, 0x0

    const/4 v3, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "bottomNavigationBarMode"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 191
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->TRANSITION_ANIMATION:Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->getId()I

    move-result v1

    sget v2, Lorg/telegram/messenger/R$string;->TransitionAnimation:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->transitionAnimations:[Ljava/lang/CharSequence;

    if-nez v3, :cond_4

    move-object/from16 v3, v16

    :cond_4
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTransitionAnimation()Lcom/exteragram/messenger/TransitionAnimation;

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

    const-string v8, "transitionAnimation"

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 192
    sget v1, Lorg/telegram/messenger/R$string;->TransitionAnimationInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 194
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->PREDICTIVE_BACK_ANIMATION:Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->getId()I

    move-result v1

    iget-object v2, v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->predictiveBackSeekbar:Lcom/exteragram/messenger/preferences/components/AltSeekbar;

    invoke-static {v1, v2}, Lorg/telegram/ui/Components/UItem;->asCustom(ILandroid/view/View;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    const-string v2, "predictiveBackAnimation"

    invoke-virtual {v1, v2, v0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-static {v14, v15, v1}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 195
    sget v1, Lorg/telegram/messenger/R$string;->PredictiveBackInfo:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-static {v14, v15, v1}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 197
    sget v1, Lorg/telegram/messenger/R$string;->AppNavigation:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 198
    sget-object v1, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->DRAWER:Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->getId()I

    move-result v1

    sget v2, Lorg/telegram/messenger/R$string;->NavigationDrawer:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getNavigationDrawer()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "navigationDrawer"

    const/4 v8, 0x0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 199
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getNavigationDrawer()Z

    move-result v12

    sget-object v0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->IMMERSIVE_ANIMATION:Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->getId()I

    move-result v1

    sget v0, Lorg/telegram/messenger/R$string;->NavigationDrawerImmersiveAnimation:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getImmersiveDrawerAnimation()Z

    move-result v3

    const-string v7, "immersiveDrawerAnimation"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;ILjava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-static {v14, v12, v1}, Lcom/exteragram/messenger/utils/ExtensionsKt;->addIf(Ljava/util/List;ZLjava/lang/Object;)V

    .line 200
    sget v0, Lorg/telegram/messenger/R$string;->NavigationDrawerInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 202
    sget v0, Lorg/telegram/messenger/R$string;->MainMenuItems:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v4

    const/4 v5, 0x1

    move-object/from16 v0, p0

    move-object/from16 v2, p2

    move-object v1, v14

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->addMenuSection(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;Ljava/lang/String;Ljava/util/ArrayList;Z)V

    .line 203
    sget v0, Lorg/telegram/messenger/R$string;->MainMenuItemsInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 205
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_5

    .line 206
    sget v0, Lorg/telegram/messenger/R$string;->MainMenuHiddenItems:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object v4

    const/4 v5, 0x0

    move-object/from16 v0, p0

    move-object/from16 v2, p2

    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->addMenuSection(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;Ljava/lang/String;Ljava/util/ArrayList;Z)V

    .line 207
    invoke-static/range {v16 .. v16}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_5
    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 131
    sget p0, Lorg/telegram/messenger/R$string;->AppNavigation:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public initializeOptionStrings()V
    .locals 8

    .line 85
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->initItemDetails()V

    .line 87
    sget v0, Lorg/telegram/messenger/R$string;->DistanceUnitsAutomatic:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 88
    sget v1, Lorg/telegram/messenger/R$string;->PasswordOn:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    .line 89
    sget v2, Lorg/telegram/messenger/R$string;->PasswordOff:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x3

    new-array v4, v3, [Ljava/lang/CharSequence;

    const/4 v5, 0x0

    aput-object v0, v4, v5

    const/4 v0, 0x1

    aput-object v1, v4, v0

    const/4 v1, 0x2

    aput-object v2, v4, v1

    .line 86
    iput-object v4, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->tabletModeOptions:[Ljava/lang/CharSequence;

    .line 92
    sget v2, Lorg/telegram/messenger/R$string;->BottomNavigationModeShow:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 93
    sget v4, Lorg/telegram/messenger/R$string;->BottomNavigationModeHide:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 94
    sget v6, Lorg/telegram/messenger/R$string;->BottomNavigationModeFloating:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-array v7, v3, [Ljava/lang/CharSequence;

    aput-object v2, v7, v5

    aput-object v4, v7, v0

    aput-object v6, v7, v1

    .line 91
    iput-object v7, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->bottomNavigationModes:[Ljava/lang/CharSequence;

    .line 97
    sget v2, Lorg/telegram/messenger/R$string;->Default:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 98
    sget v4, Lorg/telegram/messenger/R$string;->TransitionAnimationAosp:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 99
    sget v6, Lorg/telegram/messenger/R$string;->TransitionAnimationSpring:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-array v3, v3, [Ljava/lang/CharSequence;

    aput-object v2, v3, v5

    aput-object v4, v3, v0

    aput-object v6, v3, v1

    .line 96
    iput-object v3, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->transitionAnimations:[Ljava/lang/CharSequence;

    return-void
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 6

    .line 282
    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    .line 283
    sget-object p3, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;->Companion:Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem$Companion;

    invoke-virtual {p3, p2}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem$Companion;->fromId(I)Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$AppNavigationItem;

    move-result-object p3

    const/4 p4, 0x0

    if-eqz p3, :cond_9

    .line 286
    sget-object p2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p3}, Ljava/lang/Enum;->ordinal()I

    move-result p3

    aget p2, p2, p3

    const/4 p3, 0x1

    if-eq p2, p3, :cond_7

    const/4 p3, 0x2

    if-eq p2, p3, :cond_6

    const/4 p3, 0x3

    const/4 p4, 0x0

    if-eq p2, p3, :cond_4

    const/4 p3, 0x4

    if-eq p2, p3, :cond_2

    const/4 p3, 0x5

    if-eq p2, p3, :cond_0

    goto/16 :goto_4

    .line 316
    :cond_0
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->tabletModeOptions:[Ljava/lang/CharSequence;

    if-nez p2, :cond_1

    move-object v2, p4

    goto :goto_0

    :cond_1
    move-object v2, p2

    :goto_0
    sget p2, Lorg/telegram/messenger/R$string;->TabletMode:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTabletMode()I

    move-result v4

    new-instance v5, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda9;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda9;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;)V

    move-object v0, p0

    move-object v1, p1

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 306
    :cond_2
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->transitionAnimations:[Ljava/lang/CharSequence;

    if-nez p2, :cond_3

    move-object p2, p4

    :cond_3
    sget p3, Lorg/telegram/messenger/R$string;->TransitionAnimation:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getTransitionAnimation()Lcom/exteragram/messenger/TransitionAnimation;

    move-result-object p4

    invoke-virtual {p4}, Ljava/lang/Enum;->ordinal()I

    move-result p4

    new-instance p5, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda8;

    invoke-direct {p5}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda8;-><init>()V

    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 296
    :cond_4
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->bottomNavigationModes:[Ljava/lang/CharSequence;

    if-nez p2, :cond_5

    move-object p2, p4

    :cond_5
    sget p3, Lorg/telegram/messenger/R$string;->BottomNavigationBarMode:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-static {}, Lcom/exteragram/messenger/config/BottomNavigationBar;->getMode()I

    move-result p4

    new-instance p5, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda7;

    invoke-direct {p5, p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;)V

    invoke-virtual/range {p0 .. p5}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showListDialog(Lorg/telegram/ui/Components/UItem;[Ljava/lang/CharSequence;Ljava/lang/String;ILcom/exteragram/messenger/utils/ui/PopupUtils$OnItemClickListener;)V

    return-void

    .line 294
    :cond_6
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda6;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda6;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 288
    :cond_7
    new-instance p2, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda5;

    invoke-direct {p2}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$$ExternalSyntheticLambda5;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 289
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p1

    sget p2, Lorg/telegram/messenger/NotificationCenter;->mainUserInfoChanged:I

    new-array p5, p4, [Ljava/lang/Object;

    invoke-virtual {p1, p2, p5}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 290
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p1

    iget-object p1, p1, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    if-eqz p1, :cond_8

    invoke-virtual {p1, p3}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    .line 291
    :cond_8
    iget-object p0, p0, Lorg/telegram/ui/ActionBar/BaseFragment;->parentLayout:Lorg/telegram/ui/ActionBar/INavigationLayout;

    invoke-interface {p0, p4}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildFragments(I)V

    return-void

    :cond_9
    const/16 p1, -0xc8

    if-ne p2, p1, :cond_a

    .line 326
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    iget p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    add-int/lit8 p3, p2, -0x1

    iput p3, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->nextDividerId:I

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 327
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object p1

    sget-object p2, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result p2

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 328
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->saveAndNotify()V

    return-void

    :cond_a
    const/16 p1, -0x7d0

    if-gt p2, p1, :cond_10

    .line 333
    iget-object p1, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->indexOf(Ljava/lang/Object;)I

    move-result p1

    const/4 p2, -0x1

    if-eq p1, p2, :cond_f

    .line 338
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object p3

    invoke-interface {p3}, Ljava/util/Collection;->size()I

    move-result p3

    move p5, p4

    :goto_1
    if-ge p4, p3, :cond_e

    .line 339
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0, p4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    sget-object v1, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {v1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result v1

    if-nez v0, :cond_b

    goto :goto_2

    :cond_b
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v0

    if-ne v0, v1, :cond_d

    if-ne p5, p1, :cond_c

    goto :goto_3

    :cond_c
    add-int/lit8 p5, p5, 0x1

    :cond_d
    :goto_2
    add-int/lit8 p4, p4, 0x1

    goto :goto_1

    :cond_e
    move p4, p2

    :goto_3
    if-eq p4, p2, :cond_f

    .line 349
    iget-object p2, p0, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->stableDividerIds:Ljava/util/ArrayList;

    invoke-virtual {p2, p1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 350
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object p1

    invoke-virtual {p1, p4}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 351
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->saveAndNotify()V

    :cond_f
    :goto_4
    return-void

    .line 357
    :cond_10
    sget-object p1, Lcom/exteragram/messenger/MainMenuItem;->DIVIDER:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {p1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result p3

    if-ne p2, p3, :cond_11

    .line 358
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object p1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 359
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->saveAndNotify()V

    return-void

    .line 363
    :cond_11
    invoke-static {}, Lcom/exteragram/messenger/config/BottomNavigationBar;->hidden()Z

    move-result p3

    if-eqz p3, :cond_12

    sget-object p3, Lcom/exteragram/messenger/MainMenuItem;->SETTINGS:Lcom/exteragram/messenger/MainMenuItem;

    invoke-virtual {p3}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result p3

    if-ne p2, p3, :cond_12

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object p3

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p5

    invoke-virtual {p3, p5}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_12

    .line 364
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    sget p1, Lorg/telegram/messenger/R$string;->MainMenuRemoveSettingsInfo:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createErrorBulletin(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 368
    :cond_12
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object p3

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p5

    invoke-virtual {p3, p5}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_13

    .line 369
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object p3

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p5

    invoke-virtual {p3, p5}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 370
    invoke-virtual {p1}, Lcom/exteragram/messenger/MainMenuItem;->getId()I

    move-result p1

    if-eq p2, p1, :cond_14

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object p1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    invoke-virtual {p1, p3}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_14

    .line 371
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object p1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p4, p2}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    goto :goto_5

    .line 373
    :cond_13
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object p1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    invoke-virtual {p1, p3}, Ljava/util/ArrayList;->contains(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_14

    .line 374
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuHiddenItems()Ljava/util/ArrayList;

    move-result-object p1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p3

    invoke-virtual {p1, p3}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    .line 375
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getMainMenuLayout()Ljava/util/ArrayList;

    move-result-object p1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 378
    :cond_14
    :goto_5
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity;->saveAndNotify()V

    return-void
.end method
