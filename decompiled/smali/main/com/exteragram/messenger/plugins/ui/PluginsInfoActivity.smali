.class public final Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;,
        Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0007\n\u0002\u0008\u0003\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\"B\u0007\u00a2\u0006\u0004\u0008\u0003\u0010\u0004J\u0008\u0010\u0005\u001a\u00020\u0006H\u0016J\u0008\u0010\u0007\u001a\u00020\u0008H\u0016J\u0008\u0010\t\u001a\u00020\nH\u0016J5\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0016\u0010\u000f\u001a\u000c\u0012\u0008\u0008\u0001\u0012\u0004\u0018\u00010\u00110\u0010\"\u0004\u0018\u00010\u0011H\u0016\u00a2\u0006\u0002\u0010\u0012J(\u0010\u0013\u001a\u00020\n2\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\u0008\u0012\u0004\u0012\u00020\u0016`\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J0\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0014\u00a8\u0006#"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;",
        "<init>",
        "()V",
        "getTitle",
        "",
        "onFragmentCreate",
        "",
        "onFragmentDestroy",
        "",
        "didReceivedNotification",
        "id",
        "",
        "account",
        "args",
        "",
        "",
        "(II[Ljava/lang/Object;)V",
        "fillItems",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "Lkotlin/collections/ArrayList;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "Landroid/view/View;",
        "position",
        "x",
        "",
        "y",
        "PreferenceItem",
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
        "SMAP\nPluginsInfoActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginsInfoActivity.kt\ncom/exteragram/messenger/plugins/ui/PluginsInfoActivity\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,318:1\n41#2,12:319\n*S KotlinDebug\n*F\n+ 1 PluginsInfoActivity.kt\ncom/exteragram/messenger/plugins/ui/PluginsInfoActivity\n*L\n277#1:319,12\n*E\n"
    }
.end annotation


# direct methods
.method public static $r8$lambda$75FvA-8Wx6_ammvo-Vdr0N3hTJI(Landroid/content/SharedPreferences;Z)V
    .locals 2

    .line 276
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsSafeMode(Z)V

    .line 45
    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    .line 278
    const-string v0, "crashed_plugin_id"

    if-eqz p1, :cond_0

    .line 279
    const-string/jumbo v1, "manual!"

    invoke-interface {p0, v0, v1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    goto :goto_0

    .line 281
    :cond_0
    invoke-interface {p0, v0}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 50
    :goto_0
    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 284
    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->restart(Z)V

    return-void
.end method

.method public static $r8$lambda$GLVYn2y9h85S4ccCKuNG76GBJNM(Z)V
    .locals 0

    .line 268
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsUnknownSources(Z)V

    return-void
.end method

.method public static $r8$lambda$MNahqCzadSU3IcAO5MTHf7QGiw4(Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;Z)V
    .locals 1

    .line 247
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsDevMode(Z)V

    .line 248
    sget-object p1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p1

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PluginsController;->checkDevServers()V

    .line 249
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 251
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsDevMode()Z

    move-result p1

    if-eqz p1, :cond_0

    sget p1, Lorg/telegram/messenger/R$raw;->contact_check:I

    goto :goto_0

    :cond_0
    sget p1, Lorg/telegram/messenger/R$raw;->error:I

    .line 252
    :goto_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsDevMode()Z

    move-result v0

    if-eqz v0, :cond_1

    sget v0, Lorg/telegram/messenger/R$string;->PluginsDevServerLaunched:I

    goto :goto_1

    :cond_1
    sget v0, Lorg/telegram/messenger/R$string;->PluginsDevServerStopped:I

    :goto_1
    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 250
    invoke-virtual {p0, p1, v0}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 254
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static $r8$lambda$ZKxgRHkSxBXEH09P-5GrwvXrtn0(Z)V
    .locals 1

    .line 297
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsPySdkBetaVersions(Z)V

    .line 298
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->checkUpdates(Z)V

    return-void
.end method

.method public static $r8$lambda$_nJnP4umXGYo32iu7HFZxPD0DwA(Z)V
    .locals 0

    .line 265
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsUnknownSources(Z)V

    return-void
.end method

.method public static $r8$lambda$jZWitV_uc5kJ6EDjZA6AlqmjReQ(Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;Lorg/telegram/ui/Components/UItem;)Lkotlin/Unit;
    .locals 1

    .line 268
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda0;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda0;-><init>()V

    invoke-virtual {p0, p1, v0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    .line 269
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$jyKoHtubfEZWYmGRcWJUtJHu3g0(Z)V
    .locals 2

    .line 258
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsCompactView(Z)V

    .line 259
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    .line 260
    sget v0, Lorg/telegram/messenger/NotificationCenter;->reloadInterface:I

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {p0, v0, v1}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$l326Pi8wPZIW7xHkIdztqb2hsTI(Z)V
    .locals 0

    .line 294
    invoke-static {p0}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsPySdkAutoUpdate(Z)V

    return-void
.end method

.method public static $r8$lambda$oRmSB9poUPcKGSoweQ5zECk8nLc(Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;Z)V
    .locals 0

    .line 289
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsDisableArtOpts(Z)V

    .line 290
    sget-object p1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->applyArtOpts()V

    .line 291
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->showRestartBulletin()V

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 43
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public varargs didReceivedNotification(II[Ljava/lang/Object;)V
    .locals 0

    .line 85
    sget p2, Lorg/telegram/messenger/NotificationCenter;->pluginsPySdkInfoChanged:I

    if-ne p1, p2, :cond_0

    .line 86
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    const/4 p1, 0x1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    :cond_0
    return-void
.end method

.method public fillItems(Ljava/util/ArrayList;Lorg/telegram/ui/Components/UniversalAdapter;)V
    .locals 5
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

    .line 91
    sget p2, Lorg/telegram/messenger/R$string;->Settings:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 94
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->DEVELOPER_MODE:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 95
    sget v0, Lorg/telegram/messenger/R$string;->PluginsDevMode:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 96
    sget v1, Lorg/telegram/messenger/R$drawable;->msg_settings:I

    .line 93
    invoke-static {p2, v0, v1}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;I)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 98
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsDevMode()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 99
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result v0

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v0

    if-nez v0, :cond_0

    move v0, v2

    goto :goto_0

    :cond_0
    move v0, v1

    :goto_0
    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 100
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 101
    const-string/jumbo v0, "pluginsDeveloperMode"

    invoke-virtual {p2, v0, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 92
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 105
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->COMPACT_VIEW:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 106
    sget v0, Lorg/telegram/messenger/R$string;->PluginsCompactView:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 107
    sget v3, Lorg/telegram/messenger/R$drawable;->msg_topics:I

    .line 104
    invoke-static {p2, v0, v3}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;I)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 109
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsCompactView()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 110
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 111
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 112
    const-string/jumbo v0, "pluginsCompactView"

    invoke-virtual {p2, v0, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 103
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 116
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->PLUGINS_DISABLE_ART_OPTS:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 117
    sget v0, Lorg/telegram/messenger/R$string;->PluginsDisableArt:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 118
    sget v3, Lorg/telegram/messenger/R$drawable;->msg_link2:I

    .line 115
    invoke-static {p2, v0, v3}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;I)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 120
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsDisableArtOpts()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 121
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 122
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 123
    sget v0, Lorg/telegram/messenger/R$string;->PluginsDisableArtInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setValue(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 124
    invoke-virtual {p2, v2}, Lorg/telegram/ui/Components/UItem;->setMultiline(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 125
    const-string/jumbo v0, "pluginsDisableArtOpts"

    invoke-virtual {p2, v0, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 114
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 129
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->UNKNOWN_SOURCES:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 130
    sget v0, Lorg/telegram/messenger/R$string;->PluginsUnknownSources:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 131
    sget v3, Lorg/telegram/messenger/R$drawable;->msg_download:I

    .line 128
    invoke-static {p2, v0, v3}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;I)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 133
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsUnknownSources()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 134
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 135
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 136
    sget v0, Lorg/telegram/messenger/R$string;->PluginsUnknownSourcesInfo2:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setValue(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 137
    invoke-virtual {p2, v2}, Lorg/telegram/ui/Components/UItem;->setMultiline(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 138
    const-string/jumbo v0, "pluginsUnknownSources"

    invoke-virtual {p2, v0, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 127
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 142
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->SAFE_MODE:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 143
    sget v0, Lorg/telegram/messenger/R$string;->PluginsSafeMode:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 144
    sget v3, Lorg/telegram/messenger/R$drawable;->msg_secret:I

    .line 141
    invoke-static {p2, v0, v3}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;I)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 146
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 147
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 148
    const-string/jumbo v0, "pluginsSafeMode"

    invoke-virtual {p2, v0, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 140
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 150
    sget p2, Lorg/telegram/messenger/R$string;->PluginsSafeModeInfo2:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 154
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->SDK_HEADER:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 155
    const-string v0, "Plugins-Python-SDK"

    invoke-static {v0}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->markAsNewFeature(Ljava/lang/String;)Z

    move-result v0

    const-string v3, "Python SDK"

    if-eqz v0, :cond_1

    invoke-static {v3}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->applyNewSpan(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v3

    .line 153
    :cond_1
    invoke-static {p2, v3}, Lorg/telegram/ui/Components/UItem;->asHeader(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 152
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 162
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->SDK_AUTO_UPDATE:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 163
    sget v0, Lorg/telegram/messenger/R$string;->PluginsPySdkAutoUpdate:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 161
    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 165
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsPySdkAutoUpdate()Z

    move-result v0

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 166
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 167
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getStatus()I

    move-result v3

    const/4 v4, 0x3

    if-ge v3, v4, :cond_2

    move v3, v2

    goto :goto_1

    :cond_2
    move v3, v1

    :goto_1
    invoke-virtual {p2, v3}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 168
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getStateString()Ljava/lang/CharSequence;

    move-result-object v3

    invoke-virtual {p2, v3}, Lorg/telegram/ui/Components/UItem;->setValue(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 169
    invoke-virtual {p2, v2}, Lorg/telegram/ui/Components/UItem;->setMultiline(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 170
    const-string/jumbo v3, "pluginsPySdkAutoUpdate"

    invoke-virtual {p2, v3, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 160
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 174
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->SDK_BETA_VERSIONS:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 175
    sget v3, Lorg/telegram/messenger/R$string;->PluginsPySdkEnableBetaVersion:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 173
    invoke-static {p2, v3}, Lorg/telegram/ui/Components/UItem;->asCheck(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 177
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsPySdkBetaVersions()Z

    move-result v3

    invoke-virtual {p2, v3}, Lorg/telegram/ui/Components/UItem;->setChecked(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 178
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 179
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getStatus()I

    move-result v3

    if-ge v3, v4, :cond_3

    move v3, v2

    goto :goto_2

    :cond_3
    move v3, v1

    :goto_2
    invoke-virtual {p2, v3}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 180
    const-string/jumbo v3, "pluginsPySdkBetaVersions"

    invoke-virtual {p2, v3, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 172
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 184
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->CHECK_SDK_UPDATES:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 185
    sget v3, Lorg/telegram/messenger/R$string;->PluginsPySdkCheckUpdates:I

    invoke-static {v3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v3

    .line 183
    invoke-static {p2, v3}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 187
    invoke-virtual {p2}, Lorg/telegram/ui/Components/UItem;->accent()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 188
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 189
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getStatus()I

    move-result v3

    if-ge v3, v4, :cond_4

    goto :goto_3

    :cond_4
    move v2, v1

    :goto_3
    invoke-virtual {p2, v2}, Lorg/telegram/ui/Components/UItem;->setEnabled(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 190
    sget v2, Lorg/telegram/messenger/R$drawable;->msg_retry:I

    invoke-virtual {p2, v2}, Lorg/telegram/ui/Components/UItem;->setIcon(I)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 191
    const-string/jumbo v2, "pluginsPySdkCheckUpdates"

    invoke-virtual {p2, v2, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 182
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 193
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsDevMode()Z

    move-result p2

    if-eqz p2, :cond_5

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result p2

    if-nez p2, :cond_5

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->isSdkFromApk()Z

    move-result p2

    if-nez p2, :cond_5

    .line 196
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->RESTORE_SDK_FROM_APK:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 197
    sget v0, Lorg/telegram/messenger/R$string;->RestoreSdkFromApk:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 195
    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 199
    invoke-virtual {p2}, Lorg/telegram/ui/Components/UItem;->red()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 200
    sget v0, Lorg/telegram/messenger/R$drawable;->msg_reset:I

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setIcon(I)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 194
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 203
    :cond_5
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 205
    sget p2, Lorg/telegram/messenger/R$string;->Links:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    invoke-static {p2}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 208
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->DOCUMENTATION:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 209
    sget v0, Lorg/telegram/messenger/R$string;->PluginsDocumentation:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 207
    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 211
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 212
    sget v0, Lorg/telegram/messenger/R$drawable;->menu_intro:I

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setIcon(I)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 213
    const-string/jumbo v0, "pluginsDocumentation"

    invoke-virtual {p2, v0, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 206
    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 217
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->TRUSTED_PLUGINS:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getId()I

    move-result p2

    .line 218
    sget v0, Lorg/telegram/messenger/R$string;->PluginsTrusted:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    .line 216
    invoke-static {p2, v0}, Lorg/telegram/ui/Components/UItem;->asButton(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 220
    invoke-virtual {p2}, Lorg/telegram/ui/Components/UItem;->accent()Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 221
    sget v0, Lorg/telegram/messenger/R$drawable;->msg2_policy:I

    invoke-virtual {p2, v0}, Lorg/telegram/ui/Components/UItem;->setIcon(I)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 222
    invoke-virtual {p2, p0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p2

    .line 223
    const-string/jumbo v0, "trustedPlugins"

    invoke-virtual {p2, v0, p0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    .line 215
    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 227
    new-instance p0, Landroid/text/SpannableString;

    .line 229
    sget p2, Lorg/telegram/messenger/R$string;->PluginsPoweredBy:I

    invoke-static {p2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p2

    .line 228
    invoke-static {p2, v1}, Landroid/text/Html;->fromHtml(Ljava/lang/String;I)Landroid/text/Spanned;

    move-result-object p2

    .line 227
    invoke-direct {p0, p2}, Landroid/text/SpannableString;-><init>(Ljava/lang/CharSequence;)V

    .line 237
    invoke-static {p0}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->formatWithHtmlURLs(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 62
    sget p0, Lorg/telegram/messenger/R$string;->PluginsEngine:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 241
    iget p3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez p3, :cond_8

    invoke-static {}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p4

    invoke-interface {p4}, Ljava/util/List;->size()I

    move-result p4

    if-le p3, p4, :cond_0

    goto/16 :goto_2

    .line 242
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p3

    iget p4, p1, Lorg/telegram/ui/Components/UItem;->id:I

    const/4 p5, 0x1

    sub-int/2addr p4, p5

    invoke-interface {p3, p4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    .line 244
    instance-of p2, p2, Lorg/telegram/ui/Cells/TextCheckCell;

    if-eqz p2, :cond_3

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result p2

    if-nez p2, :cond_1

    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->SAFE_MODE:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    if-eq p3, p2, :cond_1

    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->SDK_AUTO_UPDATE:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    if-eq p3, p2, :cond_1

    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->SDK_BETA_VERSIONS:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    if-ne p3, p2, :cond_3

    .line 245
    :cond_1
    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p3}, Ljava/lang/Enum;->ordinal()I

    move-result p3

    aget p2, p2, p3

    packed-switch p2, :pswitch_data_0

    goto/16 :goto_2

    .line 296
    :pswitch_0
    new-instance p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda8;

    invoke-direct {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda8;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 294
    :pswitch_1
    new-instance p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda7;

    invoke-direct {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda7;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 288
    :pswitch_2
    new-instance p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda6;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda6;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;)V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 274
    :pswitch_3
    sget-object p2, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p2

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object p2

    .line 275
    new-instance p3, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda5;

    invoke-direct {p3, p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda5;-><init>(Landroid/content/SharedPreferences;)V

    invoke-virtual {p0, p1, p3}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 264
    :pswitch_4
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsUnknownSources()Z

    move-result p2

    if-eqz p2, :cond_2

    .line 265
    new-instance p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda3;

    invoke-direct {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda3;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 267
    :cond_2
    new-instance p2, Lcom/exteragram/messenger/plugins/ui/components/UnknownSourcesBottomSheet;

    .line 269
    new-instance p3, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda4;

    invoke-direct {p3, p0, p1}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda4;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;Lorg/telegram/ui/Components/UItem;)V

    .line 267
    invoke-direct {p2, p0, p3}, Lcom/exteragram/messenger/plugins/ui/components/UnknownSourcesBottomSheet;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Lkotlin/jvm/functions/Function0;)V

    .line 269
    invoke-virtual {p2}, Lorg/telegram/ui/ActionBar/BottomSheet;->show()V

    return-void

    .line 257
    :pswitch_5
    new-instance p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda2;

    invoke-direct {p2}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda2;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 246
    :pswitch_6
    new-instance p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda1;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$$ExternalSyntheticLambda1;-><init>(Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity;)V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 303
    :cond_3
    sget-object p1, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->DOCUMENTATION:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    if-eq p3, p1, :cond_6

    sget-object p2, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->TRUSTED_PLUGINS:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    if-ne p3, p2, :cond_4

    goto :goto_0

    .line 308
    :cond_4
    sget-object p1, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->CHECK_SDK_UPDATES:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    if-ne p3, p1, :cond_5

    .line 309
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {p0, p5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->checkUpdates(Z)V

    return-void

    .line 310
    :cond_5
    sget-object p1, Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;->RESTORE_SDK_FROM_APK:Lcom/exteragram/messenger/plugins/ui/PluginsInfoActivity$PreferenceItem;

    if-ne p3, p1, :cond_8

    .line 311
    sget-object p1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->restoreSdkFromApk()V

    .line 312
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    .line 313
    sget p2, Lorg/telegram/messenger/R$raw;->contact_check:I

    sget p3, Lorg/telegram/messenger/R$string;->RestartRequired:I

    invoke-static {p3}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p3

    invoke-virtual {p1, p2, p3}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p1

    .line 314
    invoke-virtual {p1}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    .line 315
    invoke-virtual {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->getListView$TMessagesProj()Lorg/telegram/ui/Components/UniversalRecyclerView;

    move-result-object p0

    iget-object p0, p0, Lorg/telegram/ui/Components/UniversalRecyclerView;->adapter:Lorg/telegram/ui/Components/UniversalAdapter;

    invoke-virtual {p0, p5}, Lorg/telegram/ui/Components/UniversalAdapter;->update(Z)V

    return-void

    .line 305
    :cond_6
    :goto_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    if-ne p3, p1, :cond_7

    .line 306
    const-string/jumbo p1, "https://plugins.exteragram.app/"

    goto :goto_1

    :cond_7
    const-string/jumbo p1, "https://t.me/addlist/pPhOtEq00KhjYTc6"

    .line 304
    :goto_1
    invoke-static {p0, p1}, Lorg/telegram/messenger/browser/Browser;->openUrl(Landroid/content/Context;Ljava/lang/String;)V

    :cond_8
    :goto_2
    return-void

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public onFragmentCreate()Z
    .locals 2

    .line 65
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentCreate()Z

    .line 67
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    .line 68
    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginsPySdkInfoChanged:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->addObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 69
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->setNotifyWhenChangeStatus(Z)V

    return v0
.end method

.method public onFragmentDestroy()V
    .locals 3

    .line 74
    invoke-super {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->onFragmentDestroy()V

    .line 76
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    .line 77
    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginsPySdkInfoChanged:I

    invoke-virtual {v0, p0, v1}, Lorg/telegram/messenger/NotificationCenter;->removeObserver(Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;I)V

    .line 78
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->setNotifyWhenChangeStatus(Z)V

    .line 79
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getStatus()I

    move-result v1

    const/4 v2, 0x2

    if-ne v1, v2, :cond_0

    .line 80
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->setStatus(I)V

    :cond_0
    return-void
.end method
