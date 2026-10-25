.class public Lcom/exteragram/messenger/backup/PreferencesUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;
    }
.end annotation


# static fields
.field private static final aiConfigKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

.field private static final configs:[Ljava/lang/String;

.field private static final excludedExteraKeys:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private static final extraExteraKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

.field private static instance:Lcom/exteragram/messenger/backup/PreferencesUtils;

.field private static final mainConfigKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

.field private static final pillStackConfigKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;


# instance fields
.field private final gson:Lcom/google/gson/Gson;


# direct methods
.method static constructor <clinit>()V
    .locals 30

    .line 71
    const-string/jumbo v17, "heapMonitorEnabled"

    const-string/jumbo v18, "heapMonitorLimitMb"

    const-string/jumbo v1, "pluginsUnknownSources"

    const-string v2, "editingIconPackId"

    const-string/jumbo v3, "iconPacksLayout"

    const-string/jumbo v4, "iconPacksHidden"

    const-string/jumbo v5, "updateScheduleTimestamp"

    const-string/jumbo v6, "sdkUpdateScheduleTimestamp"

    const-string/jumbo v7, "selectedService"

    const-string/jumbo v8, "selectedServiceId"

    const-string/jumbo v9, "lastActivePillId"

    const-string v10, "customWeatherLocation"

    const-string v11, "customWeatherAddress"

    const-string v12, "debugCameraMetrics"

    const-string v13, "forceCompactSavedMusic"

    const-string v14, "disableApiRequests"

    const-string v15, "disableChatFadeWallpaperBlend"

    const-string v16, "chatFadeUseWhiteBackground"

    filled-new-array/range {v1 .. v18}, [Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/exteragram/messenger/backup/PreferencesUtils$$ExternalSyntheticBackport0;->m([Ljava/lang/Object;)Ljava/util/Set;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/backup/PreferencesUtils;->excludedExteraKeys:Ljava/util/Set;

    .line 91
    new-instance v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string v0, "bottomNavigationBarMode"

    const-class v2, Ljava/lang/Integer;

    invoke-direct {v1, v0, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v3, "mainMenuLayout"

    const-class v4, Ljava/lang/String;

    invoke-direct {v0, v3, v4}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v3, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v5, "mainMenuHiddenItems"

    invoke-direct {v3, v5, v4}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v5, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v6, "targetLangSend"

    invoke-direct {v5, v6, v4}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v6, v5

    new-instance v5, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v7, "pluginsEngine"

    const-class v8, Ljava/lang/Boolean;

    invoke-direct {v5, v7, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v7, v6

    new-instance v6, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v9, "pinnedPlugins"

    const-class v10, Ljava/util/Set;

    invoke-direct {v6, v9, v10}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v9, v7

    new-instance v7, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v10, "hideStickerTime"

    invoke-direct {v7, v10, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v10, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v11, "tabCounter"

    invoke-direct {v10, v11, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v11, v9

    new-instance v9, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v12, "springAnimations"

    invoke-direct {v9, v12, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v12, v10

    new-instance v10, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string v13, "aospTransitions"

    invoke-direct {v10, v13, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v13, v11

    new-instance v11, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v14, "stickerCornerRoundness"

    invoke-direct {v11, v14, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v14, v12

    new-instance v12, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v15, "saveHistory"

    invoke-direct {v12, v15, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v15, v13

    new-instance v13, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v16, v0

    const-string/jumbo v0, "responseStreaming"

    invoke-direct {v13, v0, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v0, v14

    new-instance v14, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v17, v0

    const-string/jumbo v0, "temperature"

    invoke-direct {v14, v0, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object v0, v15

    new-instance v15, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v18, v0

    const-string/jumbo v0, "showResponseOnly"

    invoke-direct {v15, v0, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v19, v1

    const-string/jumbo v1, "insertAsQuote"

    invoke-direct {v0, v1, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v20, v0

    const-string/jumbo v0, "onDeviceReasoning"

    invoke-direct {v1, v0, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v21, v1

    const-string/jumbo v1, "onDevicePreviewModel"

    invoke-direct {v0, v1, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v22, v0

    const-string/jumbo v0, "onDeviceFastModel"

    invoke-direct {v1, v0, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v23, v1

    const-string/jumbo v1, "replaceTelegramEditor"

    invoke-direct {v0, v1, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v24, v0

    const-string/jumbo v0, "replaceTelegramSummaries"

    invoke-direct {v1, v0, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v25, v1

    const-string/jumbo v1, "selectedRole"

    invoke-direct {v0, v1, v4}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v26, v0

    const-string/jumbo v0, "infiniteScrolling"

    invoke-direct {v1, v0, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v27, v1

    const-string/jumbo v1, "useCurrentLocation"

    invoke-direct {v0, v1, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v8, "gramTargetCurrency"

    invoke-direct {v1, v8, v4}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v8, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v28, v0

    const-string v0, "btcTargetCurrency"

    invoke-direct {v8, v0, v4}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-object/from16 v29, v1

    const-string/jumbo v1, "usdTargetCurrency"

    invoke-direct {v0, v1, v4}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    move-object/from16 v1, v28

    move-object/from16 v28, v2

    move-object/from16 v2, v16

    move-object/from16 v16, v20

    move-object/from16 v20, v24

    move-object/from16 v24, v1

    move-object/from16 v1, v19

    move-object/from16 v19, v23

    move-object/from16 v23, v27

    move-object/from16 v27, v0

    move-object v0, v4

    move-object/from16 v4, v18

    move-object/from16 v18, v22

    move-object/from16 v22, v26

    move-object/from16 v26, v8

    move-object/from16 v8, v17

    move-object/from16 v17, v21

    move-object/from16 v21, v25

    move-object/from16 v25, v29

    filled-new-array/range {v1 .. v27}, [Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/backup/PreferencesUtils;->extraExteraKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    .line 122
    new-instance v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v2, "roles"

    invoke-direct {v1, v2, v0}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    filled-new-array {v1}, [Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/backup/PreferencesUtils;->aiConfigKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    .line 125
    new-instance v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string v2, "activePills"

    invoke-direct {v1, v2, v0}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v2, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v3, "hiddenPills"

    invoke-direct {v2, v3, v0}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    filled-new-array {v1, v2}, [Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/backup/PreferencesUtils;->pillStackConfigKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    .line 129
    new-instance v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string v1, "ChatSwipeAction"

    move-object/from16 v2, v28

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string/jumbo v3, "mediaColumnsCount"

    invoke-direct {v1, v3, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v3, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string v4, "bubbleRadius"

    invoke-direct {v3, v4, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    new-instance v4, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    const-string v5, "fons_size"

    invoke-direct {v4, v5, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;-><init>(Ljava/lang/String;Ljava/lang/Class;)V

    filled-new-array {v0, v1, v3, v4}, [Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/backup/PreferencesUtils;->mainConfigKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    .line 135
    const-string/jumbo v0, "pillstackconfig"

    const-string/jumbo v1, "mainconfig"

    const-string v2, "exteraconfig"

    const-string v3, "aiConfig"

    filled-new-array {v2, v3, v0, v1}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/backup/PreferencesUtils;->configs:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 67
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 142
    new-instance v0, Lcom/google/gson/GsonBuilder;

    invoke-direct {v0}, Lcom/google/gson/GsonBuilder;-><init>()V

    invoke-virtual {v0}, Lcom/google/gson/GsonBuilder;->setPrettyPrinting()Lcom/google/gson/GsonBuilder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/gson/GsonBuilder;->create()Lcom/google/gson/Gson;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->gson:Lcom/google/gson/Gson;

    return-void
.end method

.method private checkKeys(Lcom/google/gson/JsonObject;)Z
    .locals 11

    .line 623
    sget-object v0, Lcom/exteragram/messenger/backup/PreferencesUtils;->configs:[Ljava/lang/String;

    array-length v1, v0

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_2

    aget-object v4, v0, v3

    .line 624
    invoke-direct {p0, p1, v4}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getConfigObject(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;

    move-result-object v5

    if-eqz v5, :cond_1

    .line 626
    invoke-virtual {v5}, Lcom/google/gson/JsonObject;->keySet()Ljava/util/Set;

    move-result-object v6

    invoke-interface {v6}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v6

    :goto_1
    invoke-interface {v6}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_1

    invoke-interface {v6}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    .line 627
    invoke-virtual {v5, v7}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v8

    .line 628
    invoke-direct {p0, v4, v7, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedValue(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_0

    const/4 p0, 0x1

    return p0

    .line 631
    :cond_0
    new-instance v9, Ljava/lang/StringBuilder;

    const-string v10, "Unexpected value: "

    invoke-direct {v9, v10}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v9, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v7, " "

    invoke-virtual {v9, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    goto :goto_1

    :cond_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_2
    return v2
.end method

.method public static clearPreferences()V
    .locals 1

    .line 152
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->clear()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 153
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->clear()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 154
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->clear()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 155
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->reloadConfig()V

    .line 156
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->reloadConfig()V

    return-void
.end method

.method private findBackupItem(Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;
    .locals 5

    .line 177
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_a

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_3

    .line 180
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result v0

    const/4 v2, 0x0

    const/4 v3, -0x1

    sparse-switch v0, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    const-string/jumbo v0, "mainconfig"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_1

    goto :goto_0

    :cond_1
    const/4 v3, 0x3

    goto :goto_0

    :sswitch_1
    const-string v0, "exteraconfig"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    goto :goto_0

    :cond_2
    const/4 v3, 0x2

    goto :goto_0

    :sswitch_2
    const-string/jumbo v0, "pillstackconfig"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_3

    goto :goto_0

    :cond_3
    const/4 v3, 0x1

    goto :goto_0

    :sswitch_3
    const-string v0, "aiConfig"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_4

    goto :goto_0

    :cond_4
    move v3, v2

    :goto_0
    packed-switch v3, :pswitch_data_0

    return-object v1

    .line 204
    :pswitch_0
    sget-object p1, Lcom/exteragram/messenger/backup/PreferencesUtils;->mainConfigKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->findBackupItem([Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;Ljava/lang/String;)Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object p0

    return-object p0

    .line 182
    :pswitch_1
    sget-object p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->extraExteraKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    array-length p1, p0

    move v0, v2

    :goto_1
    if-ge v0, p1, :cond_6

    aget-object v3, p0, v0

    .line 183
    iget-object v4, v3, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->key:Ljava/lang/String;

    invoke-virtual {v4, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_5

    return-object v3

    :cond_5
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 187
    :cond_6
    sget-object p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->excludedExteraKeys:Ljava/util/Set;

    invoke-interface {p0, p2}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_7

    return-object v1

    .line 190
    :cond_7
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getBackupKeys()[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object p0

    array-length p1, p0

    :goto_2
    if-ge v2, p1, :cond_9

    aget-object v0, p0, v2

    .line 191
    iget-object v3, v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->key:Ljava/lang/String;

    invoke-virtual {v3, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_8

    return-object v0

    :cond_8
    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_9
    return-object v1

    .line 201
    :pswitch_2
    sget-object p1, Lcom/exteragram/messenger/backup/PreferencesUtils;->pillStackConfigKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->findBackupItem([Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;Ljava/lang/String;)Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object p0

    return-object p0

    .line 198
    :pswitch_3
    sget-object p1, Lcom/exteragram/messenger/backup/PreferencesUtils;->aiConfigKeys:[Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->findBackupItem([Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;Ljava/lang/String;)Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object p0

    return-object p0

    :cond_a
    :goto_3
    return-object v1

    :sswitch_data_0
    .sparse-switch
        -0x789b4276 -> :sswitch_3
        -0x74510f0f -> :sswitch_2
        0x1530895 -> :sswitch_1
        0x36bb9a3b -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private findBackupItem([Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;Ljava/lang/String;)Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;
    .locals 3

    .line 211
    array-length p0, p1

    const/4 v0, 0x0

    :goto_0
    if-ge v0, p0, :cond_1

    aget-object v1, p1, v0

    .line 212
    iget-object v2, v1, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->key:Ljava/lang/String;

    invoke-virtual {v2, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    return-object v1

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public static generateBackupName(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 171
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const-string p0, "backup"

    :goto_0
    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "-"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 p0, 0x4

    .line 172
    invoke-static {p0}, Lorg/telegram/messenger/Utilities;->generateRandomString(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ".extera"

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private getConfigObject(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;
    .locals 1

    .line 579
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->has(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->isJsonObject()Z

    move-result p0

    if-eqz p0, :cond_0

    .line 580
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->getAsJsonObject(Ljava/lang/String;)Lcom/google/gson/JsonObject;

    move-result-object p0

    return-object p0

    .line 582
    :cond_0
    const-string/jumbo p0, "mainconfig"

    invoke-virtual {p0, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_2

    .line 583
    invoke-virtual {p1}, Lcom/google/gson/JsonObject;->keySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    .line 584
    const-string v0, "^mainconfig\\d+$"

    invoke-virtual {p2, v0}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/gson/JsonElement;->isJsonObject()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 585
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->getAsJsonObject(Ljava/lang/String;)Lcom/google/gson/JsonObject;

    move-result-object p0

    return-object p0

    :cond_2
    const/4 p0, 0x0

    return-object p0
.end method

.method private static getContext()Landroid/content/Context;
    .locals 1

    .line 160
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    if-eqz v0, :cond_0

    .line 161
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    return-object v0

    .line 163
    :cond_0
    invoke-static {}, Lorg/telegram/messenger/AndroidUtilities;->getActivity()Landroid/app/Activity;

    move-result-object v0

    return-object v0
.end method

.method private getExactInteger(Lcom/google/gson/JsonElement;)Ljava/lang/Integer;
    .locals 1

    .line 259
    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->isJsonPrimitive()Z

    move-result p0

    const/4 v0, 0x0

    if-eqz p0, :cond_1

    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->getAsJsonPrimitive()Lcom/google/gson/JsonPrimitive;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonPrimitive;->isNumber()Z

    move-result p0

    if-nez p0, :cond_0

    goto :goto_0

    .line 263
    :cond_0
    :try_start_0
    new-instance p0, Ljava/math/BigDecimal;

    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/math/BigDecimal;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0}, Ljava/math/BigDecimal;->intValueExact()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/ArithmeticException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    :cond_1
    :goto_0
    return-object v0
.end method

.method private getExactLong(Lcom/google/gson/JsonElement;)Ljava/lang/Long;
    .locals 1

    .line 270
    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->isJsonPrimitive()Z

    move-result p0

    const/4 v0, 0x0

    if-eqz p0, :cond_1

    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->getAsJsonPrimitive()Lcom/google/gson/JsonPrimitive;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonPrimitive;->isNumber()Z

    move-result p0

    if-nez p0, :cond_0

    goto :goto_0

    .line 274
    :cond_0
    :try_start_0
    new-instance p0, Ljava/math/BigDecimal;

    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/math/BigDecimal;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0}, Ljava/math/BigDecimal;->longValueExact()J

    move-result-wide p0

    invoke-static {p0, p1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/ArithmeticException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    :cond_1
    :goto_0
    return-object v0
.end method

.method public static getInstance()Lcom/exteragram/messenger/backup/PreferencesUtils;
    .locals 1

    .line 145
    sget-object v0, Lcom/exteragram/messenger/backup/PreferencesUtils;->instance:Lcom/exteragram/messenger/backup/PreferencesUtils;

    if-nez v0, :cond_0

    .line 146
    new-instance v0, Lcom/exteragram/messenger/backup/PreferencesUtils;

    invoke-direct {v0}, Lcom/exteragram/messenger/backup/PreferencesUtils;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/backup/PreferencesUtils;->instance:Lcom/exteragram/messenger/backup/PreferencesUtils;

    .line 148
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/backup/PreferencesUtils;->instance:Lcom/exteragram/messenger/backup/PreferencesUtils;

    return-object v0
.end method

.method public static getPreferences(Ljava/lang/String;)Landroid/content/SharedPreferences;
    .locals 2

    .line 167
    invoke-static {}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getContext()Landroid/content/Context;

    move-result-object v0

    const/4 v1, 0x0

    invoke-virtual {v0, p0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object p0

    return-object p0
.end method

.method private importConfig(Lcom/google/gson/JsonObject;Ljava/lang/String;)V
    .locals 7

    .line 544
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getConfigObject(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;

    move-result-object p1

    if-eqz p1, :cond_8

    .line 546
    invoke-static {p2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getPreferences(Ljava/lang/String;)Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 547
    invoke-virtual {p1}, Lcom/google/gson/JsonObject;->keySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_7

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 548
    invoke-virtual {p1, v2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v3

    .line 549
    invoke-direct {p0, p2, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->findBackupItem(Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object v4

    if-eqz v4, :cond_0

    .line 550
    invoke-direct {p0, p2, v2, v3}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedValue(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_1

    goto :goto_0

    .line 553
    :cond_1
    iget-object v5, v4, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->clazz:Ljava/lang/Class;

    const-class v6, Ljava/lang/Boolean;

    invoke-virtual {v5, v6}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_2

    .line 554
    invoke-virtual {v3}, Lcom/google/gson/JsonElement;->getAsBoolean()Z

    move-result v3

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    goto :goto_2

    .line 555
    :cond_2
    iget-object v5, v4, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->clazz:Ljava/lang/Class;

    const-class v6, Ljava/lang/Float;

    invoke-virtual {v5, v6}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 556
    invoke-virtual {v3}, Lcom/google/gson/JsonElement;->getAsFloat()F

    move-result v3

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putFloat(Ljava/lang/String;F)Landroid/content/SharedPreferences$Editor;

    goto :goto_2

    .line 557
    :cond_3
    iget-object v5, v4, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->clazz:Ljava/lang/Class;

    const-class v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_4

    .line 558
    invoke-virtual {v3}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object v3

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    goto :goto_2

    .line 559
    :cond_4
    iget-object v4, v4, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->clazz:Ljava/lang/Class;

    const-class v5, Ljava/util/Set;

    invoke-virtual {v4, v5}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_6

    .line 560
    new-instance v4, Ljava/util/HashSet;

    invoke-direct {v4}, Ljava/util/HashSet;-><init>()V

    .line 561
    invoke-virtual {v3}, Lcom/google/gson/JsonElement;->getAsJsonArray()Lcom/google/gson/JsonArray;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/gson/JsonArray;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_5

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/google/gson/JsonElement;

    .line 562
    invoke-virtual {v5}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 564
    :cond_5
    invoke-interface {v0, v2, v4}, Landroid/content/SharedPreferences$Editor;->putStringSet(Ljava/lang/String;Ljava/util/Set;)Landroid/content/SharedPreferences$Editor;

    goto :goto_2

    .line 566
    :cond_6
    invoke-virtual {v3}, Lcom/google/gson/JsonElement;->getAsInt()I

    move-result v3

    invoke-interface {v0, v2, v3}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 569
    :goto_2
    const-string v3, "exteraconfig"

    invoke-virtual {v3, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    const-string/jumbo v3, "iconPack"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 570
    const-string/jumbo v2, "iconPacksLayout"

    invoke-interface {v0, v2}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 571
    const-string/jumbo v2, "iconPacksHidden"

    invoke-interface {v0, v2}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    goto/16 :goto_0

    .line 574
    :cond_7
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    :cond_8
    return-void
.end method

.method private isExpectedFloat(Ljava/lang/String;F)Z
    .locals 3

    .line 281
    invoke-static {p2}, Ljava/lang/Float;->isFinite(F)Z

    move-result p0

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 284
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result p0

    const/4 v1, 0x1

    const/4 v2, -0x1

    sparse-switch p0, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    const-string/jumbo p0, "predictiveBackIntensity"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    goto :goto_0

    :cond_1
    const/4 v2, 0x5

    goto :goto_0

    :sswitch_1
    const-string/jumbo p0, "stickerSize"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_2

    goto :goto_0

    :cond_2
    const/4 v2, 0x4

    goto :goto_0

    :sswitch_2
    const-string p0, "flashWarmth"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_3

    goto :goto_0

    :cond_3
    const/4 v2, 0x3

    goto :goto_0

    :sswitch_3
    const-string p0, "flashIntensity"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_4

    goto :goto_0

    :cond_4
    const/4 v2, 0x2

    goto :goto_0

    :sswitch_4
    const-string p0, "avatarCorners"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_5

    goto :goto_0

    :cond_5
    move v2, v1

    goto :goto_0

    :sswitch_5
    const-string/jumbo p0, "sectionRadius"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_6

    goto :goto_0

    :cond_6
    move v2, v0

    :goto_0
    const/high16 p0, 0x41e00000    # 28.0f

    const/4 p1, 0x0

    packed-switch v2, :pswitch_data_0

    return v0

    :pswitch_0
    cmpl-float p0, p2, p1

    if-ltz p0, :cond_7

    const/high16 p0, 0x40a00000    # 5.0f

    cmpg-float p0, p2, p0

    if-gtz p0, :cond_7

    return v1

    :cond_7
    return v0

    :pswitch_1
    const/high16 p0, 0x40800000    # 4.0f

    cmpl-float p0, p2, p0

    if-ltz p0, :cond_8

    const/high16 p0, 0x41a00000    # 20.0f

    cmpg-float p0, p2, p0

    if-gtz p0, :cond_8

    return v1

    :cond_8
    return v0

    :pswitch_2
    cmpl-float p0, p2, p1

    if-ltz p0, :cond_9

    const/high16 p0, 0x3f800000    # 1.0f

    cmpg-float p0, p2, p0

    if-gtz p0, :cond_9

    return v1

    :cond_9
    return v0

    :pswitch_3
    cmpl-float p1, p2, p1

    if-ltz p1, :cond_a

    cmpg-float p0, p2, p0

    if-gtz p0, :cond_a

    return v1

    :cond_a
    return v0

    :pswitch_4
    cmpl-float p1, p2, p1

    if-ltz p1, :cond_b

    cmpg-float p0, p2, p0

    if-gtz p0, :cond_b

    return v1

    :cond_b
    return v0

    :sswitch_data_0
    .sparse-switch
        -0x6540a829 -> :sswitch_5
        0x13d02925 -> :sswitch_4
        0x3dbc3623 -> :sswitch_3
        0x51ba3bc9 -> :sswitch_2
        0x5f2b76de -> :sswitch_1
        0x75fd5fed -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private isExpectedInteger(Ljava/lang/String;I)Z
    .locals 13

    .line 295
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result p0

    const/16 v0, 0x14

    const/16 v1, 0x11

    const/16 v2, 0xc

    const/16 v3, 0x8

    const/4 v4, 0x7

    const/4 v5, 0x5

    const/4 v6, 0x4

    const/4 v7, 0x3

    const/4 v8, 0x2

    const/16 v9, 0x9

    const/4 v10, 0x1

    const/4 v11, 0x0

    const/4 v12, -0x1

    sparse-switch p0, :sswitch_data_0

    goto/16 :goto_0

    :sswitch_0
    const-string/jumbo p0, "showIdAndDc"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_0

    goto/16 :goto_0

    :cond_0
    const/16 v12, 0x1a

    goto/16 :goto_0

    :sswitch_1
    const-string p0, "bottomButton"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1

    goto/16 :goto_0

    :cond_1
    const/16 v12, 0x19

    goto/16 :goto_0

    :sswitch_2
    const-string/jumbo p0, "translationProvider"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_2

    goto/16 :goto_0

    :cond_2
    const/16 v12, 0x18

    goto/16 :goto_0

    :sswitch_3
    const-string p0, "doubleTapSeekDuration"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_3

    goto/16 :goto_0

    :cond_3
    const/16 v12, 0x17

    goto/16 :goto_0

    :sswitch_4
    const-string p0, "bottomNavigationBarMode"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_4

    goto/16 :goto_0

    :cond_4
    const/16 v12, 0x16

    goto/16 :goto_0

    :sswitch_5
    const-string p0, "doubleTapActionOutOwner"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_5

    goto/16 :goto_0

    :cond_5
    const/16 v12, 0x15

    goto/16 :goto_0

    :sswitch_6
    const-string/jumbo p0, "temperature"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_6

    goto/16 :goto_0

    :cond_6
    move v12, v0

    goto/16 :goto_0

    :sswitch_7
    const-string/jumbo p0, "stickerCornerRoundness"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_7

    goto/16 :goto_0

    :cond_7
    const/16 v12, 0x13

    goto/16 :goto_0

    :sswitch_8
    const-string p0, "eventType"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_8

    goto/16 :goto_0

    :cond_8
    const/16 v12, 0x12

    goto/16 :goto_0

    :sswitch_9
    const-string/jumbo p0, "glassOutlineStyle"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_9

    goto/16 :goto_0

    :cond_9
    move v12, v1

    goto/16 :goto_0

    :sswitch_a
    const-string p0, "doNotUseProxy"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_a

    goto/16 :goto_0

    :cond_a
    const/16 v12, 0x10

    goto/16 :goto_0

    :sswitch_b
    const-string/jumbo p0, "iconPack"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_b

    goto/16 :goto_0

    :cond_b
    const/16 v12, 0xf

    goto/16 :goto_0

    :sswitch_c
    const-string p0, "doubleTapAction"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_c

    goto/16 :goto_0

    :cond_c
    const/16 v12, 0xe

    goto/16 :goto_0

    :sswitch_d
    const-string/jumbo p0, "tabIcons"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_d

    goto/16 :goto_0

    :cond_d
    const/16 v12, 0xd

    goto/16 :goto_0

    :sswitch_e
    const-string p0, "dividerStyle"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_e

    goto/16 :goto_0

    :cond_e
    move v12, v2

    goto/16 :goto_0

    :sswitch_f
    const-string/jumbo p0, "transitionAnimation"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_f

    goto/16 :goto_0

    :cond_f
    const/16 v12, 0xb

    goto/16 :goto_0

    :sswitch_10
    const-string/jumbo p0, "videoMessagesCamera"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_10

    goto/16 :goto_0

    :cond_10
    const/16 v12, 0xa

    goto/16 :goto_0

    :sswitch_11
    const-string/jumbo p0, "stickerTimeMode"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_11

    goto/16 :goto_0

    :cond_11
    move v12, v9

    goto/16 :goto_0

    :sswitch_12
    const-string/jumbo p0, "tabletMode"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_12

    goto/16 :goto_0

    :cond_12
    move v12, v3

    goto/16 :goto_0

    :sswitch_13
    const-string p0, "fons_size"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_13

    goto/16 :goto_0

    :cond_13
    move v12, v4

    goto :goto_0

    :sswitch_14
    const-string p0, "downloadSpeedBoost"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_14

    goto :goto_0

    :cond_14
    const/4 v12, 0x6

    goto :goto_0

    :sswitch_15
    const-string/jumbo p0, "mediaColumnsCount"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_15

    goto :goto_0

    :cond_15
    move v12, v5

    goto :goto_0

    :sswitch_16
    const-string p0, "ChatSwipeAction"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_16

    goto :goto_0

    :cond_16
    move v12, v6

    goto :goto_0

    :sswitch_17
    const-string p0, "bubbleRadius"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_17

    goto :goto_0

    :cond_17
    move v12, v7

    goto :goto_0

    :sswitch_18
    const-string p0, "cameraType"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_18

    goto :goto_0

    :cond_18
    move v12, v8

    goto :goto_0

    :sswitch_19
    const-string/jumbo p0, "stickerShape"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_19

    goto :goto_0

    :cond_19
    move v12, v10

    goto :goto_0

    :sswitch_1a
    const-string/jumbo p0, "titleText"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_1a

    goto :goto_0

    :cond_1a
    move v12, v11

    :goto_0
    packed-switch v12, :pswitch_data_0

    return v11

    :pswitch_0
    if-ltz p2, :cond_1b

    .line 300
    invoke-static {}, Lcom/exteragram/messenger/translator/TranslationProviders;->getLastIndex()I

    move-result p0

    if-gt p2, p0, :cond_1b

    return v10

    :cond_1b
    return v11

    :pswitch_1
    if-ltz p2, :cond_1c

    if-gt p2, v9, :cond_1c

    return v10

    :cond_1c
    return v11

    :pswitch_2
    if-ltz p2, :cond_1d

    if-gt p2, v0, :cond_1d

    return v10

    :cond_1d
    return v11

    :pswitch_3
    if-ltz p2, :cond_1e

    if-gt p2, v6, :cond_1e

    return v10

    :cond_1e
    return v11

    :pswitch_4
    if-ltz p2, :cond_1f

    if-gt p2, v4, :cond_1f

    return v10

    :cond_1f
    return v11

    :pswitch_5
    if-ltz p2, :cond_20

    if-gt p2, v3, :cond_20

    return v10

    :cond_20
    return v11

    :pswitch_6
    if-lt p2, v2, :cond_21

    const/16 p0, 0x1e

    if-gt p2, p0, :cond_21

    return v10

    :cond_21
    return v11

    :pswitch_7
    if-lt p2, v8, :cond_22

    if-gt p2, v9, :cond_22

    return v10

    :cond_22
    return v11

    :pswitch_8
    if-ltz p2, :cond_23

    if-gt p2, v5, :cond_23

    return v10

    :cond_23
    return v11

    :pswitch_9
    if-ltz p2, :cond_24

    if-gt p2, v1, :cond_24

    return v10

    :cond_24
    return v11

    :pswitch_a
    if-ltz p2, :cond_25

    if-gt p2, v8, :cond_25

    return v10

    :cond_25
    return v11

    :pswitch_b
    if-ltz p2, :cond_26

    if-gt p2, v7, :cond_26

    return v10

    :cond_26
    return v11

    nop

    :sswitch_data_0
    .sparse-switch
        -0x7f4d105b -> :sswitch_1a
        -0x79bd6b9c -> :sswitch_19
        -0x77ee5401 -> :sswitch_18
        -0x77e18bc2 -> :sswitch_17
        -0x730c6488 -> :sswitch_16
        -0x68f3c6ca -> :sswitch_15
        -0x63591dbc -> :sswitch_14
        -0x5d7df2ae -> :sswitch_13
        -0x5895b6d7 -> :sswitch_12
        -0x51704d93 -> :sswitch_11
        -0x4d278b14 -> :sswitch_10
        -0x4b310251 -> :sswitch_f
        -0x48c6dde8 -> :sswitch_e
        -0x374ce77b -> :sswitch_d
        -0x35b2fd18 -> :sswitch_c
        -0x2bfdd1ce -> :sswitch_b
        -0x141b1c71 -> :sswitch_a
        -0x894ffd5 -> :sswitch_9
        0x1df98f4 -> :sswitch_8
        0x12feaf13 -> :sswitch_7
        0x132cc574 -> :sswitch_6
        0x1a68b30d -> :sswitch_5
        0x263d98b7 -> :sswitch_4
        0x2dba3f5e -> :sswitch_3
        0x36871bc2 -> :sswitch_2
        0x6965213d -> :sswitch_1
        0x77fd3a9e -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_b
        :pswitch_a
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_a
        :pswitch_6
        :pswitch_a
        :pswitch_a
        :pswitch_a
        :pswitch_a
        :pswitch_a
        :pswitch_a
        :pswitch_5
        :pswitch_a
        :pswitch_4
        :pswitch_a
        :pswitch_3
        :pswitch_b
        :pswitch_2
        :pswitch_1
        :pswitch_a
        :pswitch_b
        :pswitch_0
        :pswitch_a
        :pswitch_a
    .end packed-switch
.end method

.method private isExpectedMainMenuLayout(Ljava/lang/String;Z)Z
    .locals 5

    .line 373
    iget-object v0, p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->gson:Lcom/google/gson/Gson;

    const-class v1, Lcom/google/gson/JsonElement;

    invoke-virtual {v0, p1, v1}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/google/gson/JsonElement;

    const/4 v0, 0x0

    if-eqz p1, :cond_5

    .line 374
    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->isJsonArray()Z

    move-result v1

    if-eqz v1, :cond_5

    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->getAsJsonArray()Lcom/google/gson/JsonArray;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/gson/JsonArray;->size()I

    move-result v1

    const/16 v2, 0x64

    if-le v1, v2, :cond_0

    goto :goto_0

    .line 377
    :cond_0
    new-instance v1, Ljava/util/HashSet;

    invoke-direct {v1}, Ljava/util/HashSet;-><init>()V

    .line 378
    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->getAsJsonArray()Lcom/google/gson/JsonArray;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/gson/JsonArray;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/gson/JsonElement;

    .line 379
    invoke-direct {p0, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getExactInteger(Lcom/google/gson/JsonElement;)Ljava/lang/Integer;

    move-result-object v2

    if-nez v2, :cond_2

    return v0

    .line 383
    :cond_2
    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v3

    const/4 v4, -0x1

    if-ne v3, v4, :cond_3

    if-nez p2, :cond_1

    return v0

    .line 387
    :cond_3
    invoke-virtual {v1, v2}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_1

    return v0

    :cond_4
    const/4 p0, 0x1

    return p0

    :cond_5
    :goto_0
    return v0
.end method

.method private isExpectedPillsLayout(Ljava/lang/String;)Z
    .locals 5

    .line 433
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p0

    const/16 v0, 0x1000

    if-gt p0, v0, :cond_5

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_2

    .line 436
    :cond_0
    new-instance p0, Ljava/util/HashSet;

    invoke-direct {p0}, Ljava/util/HashSet;-><init>()V

    .line 437
    const-string v0, ","

    invoke-virtual {p1, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    array-length v0, p1

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    if-ge v2, v0, :cond_3

    aget-object v3, p1, v2

    .line 439
    :try_start_0
    invoke-virtual {v3}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v3

    if-lez v3, :cond_2

    const v4, 0x186a0

    if-gt v3, v4, :cond_2

    .line 440
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {p0, v3}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move-result v3
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    if-nez v3, :cond_1

    goto :goto_1

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :catch_0
    :cond_2
    :goto_1
    return v1

    .line 447
    :cond_3
    invoke-virtual {p0}, Ljava/util/HashSet;->size()I

    move-result p0

    const/16 p1, 0x64

    if-gt p0, p1, :cond_4

    const/4 p0, 0x1

    return p0

    :cond_4
    return v1

    .line 434
    :cond_5
    :goto_2
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    return p0
.end method

.method private isExpectedRoles(Ljava/lang/String;)Z
    .locals 6

    .line 395
    iget-object v0, p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->gson:Lcom/google/gson/Gson;

    const-class v1, Lcom/google/gson/JsonElement;

    invoke-virtual {v0, p1, v1}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/google/gson/JsonElement;

    const/4 v0, 0x0

    if-eqz p1, :cond_7

    .line 396
    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->isJsonArray()Z

    move-result v1

    if-eqz v1, :cond_7

    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->getAsJsonArray()Lcom/google/gson/JsonArray;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/gson/JsonArray;->size()I

    move-result v1

    const/16 v2, 0x64

    if-le v1, v2, :cond_0

    goto/16 :goto_1

    .line 399
    :cond_0
    new-instance v1, Ljava/util/HashSet;

    invoke-direct {v1}, Ljava/util/HashSet;-><init>()V

    .line 400
    invoke-virtual {p1}, Lcom/google/gson/JsonElement;->getAsJsonArray()Lcom/google/gson/JsonArray;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/gson/JsonArray;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_6

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/gson/JsonElement;

    .line 401
    invoke-virtual {v2}, Lcom/google/gson/JsonElement;->isJsonObject()Z

    move-result v3

    if-nez v3, :cond_2

    return v0

    .line 404
    :cond_2
    invoke-virtual {v2}, Lcom/google/gson/JsonElement;->getAsJsonObject()Lcom/google/gson/JsonObject;

    move-result-object v2

    const/16 v3, 0x100

    .line 405
    const-string/jumbo v4, "name"

    invoke-direct {p0, v2, v4, v3}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isRequiredString(Lcom/google/gson/JsonObject;Ljava/lang/String;I)Z

    move-result v3

    if-eqz v3, :cond_5

    const-string/jumbo v3, "prompt"

    const/high16 v5, 0x10000

    .line 406
    invoke-direct {p0, v2, v3, v5}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isRequiredString(Lcom/google/gson/JsonObject;Ljava/lang/String;I)Z

    move-result v3

    if-eqz v3, :cond_5

    .line 407
    invoke-virtual {v2, v4}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v3

    invoke-virtual {v3}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_3

    goto :goto_0

    .line 410
    :cond_3
    const-string v3, "emojiId"

    invoke-virtual {v2, v3}, Lcom/google/gson/JsonObject;->has(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_4

    invoke-virtual {v2, v3}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/gson/JsonElement;->isJsonNull()Z

    move-result v4

    if-nez v4, :cond_4

    invoke-virtual {v2, v3}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v3

    invoke-direct {p0, v3}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getExactLong(Lcom/google/gson/JsonElement;)Ljava/lang/Long;

    move-result-object v3

    if-nez v3, :cond_4

    return v0

    .line 413
    :cond_4
    const-string/jumbo v3, "isSuggestion"

    invoke-virtual {v2, v3}, Lcom/google/gson/JsonObject;->has(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_1

    .line 414
    invoke-virtual {v2, v3}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/gson/JsonElement;->isJsonNull()Z

    move-result v4

    if-nez v4, :cond_1

    .line 415
    invoke-virtual {v2, v3}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v4

    invoke-virtual {v4}, Lcom/google/gson/JsonElement;->isJsonPrimitive()Z

    move-result v4

    if-eqz v4, :cond_5

    .line 416
    invoke-virtual {v2, v3}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/gson/JsonElement;->getAsJsonPrimitive()Lcom/google/gson/JsonPrimitive;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/gson/JsonPrimitive;->isBoolean()Z

    move-result v2

    if-nez v2, :cond_1

    :cond_5
    :goto_0
    return v0

    :cond_6
    const/4 p0, 0x1

    return p0

    :cond_7
    :goto_1
    return v0
.end method

.method private isExpectedString(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    .locals 4

    const/4 v0, 0x0

    if-eqz p3, :cond_11

    .line 316
    invoke-virtual {p3}, Ljava/lang/String;->length()I

    move-result v1

    const/high16 v2, 0x100000

    if-le v1, v2, :cond_0

    goto/16 :goto_4

    .line 319
    :cond_0
    invoke-virtual {p2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {p2}, Ljava/lang/String;->hashCode()I

    move-result v1

    const/4 v2, 0x1

    const/4 v3, -0x1

    sparse-switch v1, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    const-string/jumbo v1, "mainMenuHiddenItems"

    invoke-virtual {p2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    :cond_1
    const/4 v3, 0x6

    goto :goto_0

    :sswitch_1
    const-string/jumbo v1, "targetLangSend"

    invoke-virtual {p2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_0

    :cond_2
    const/4 v3, 0x5

    goto :goto_0

    :sswitch_2
    const-string/jumbo v1, "targetLang"

    invoke-virtual {p2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    goto :goto_0

    :cond_3
    const/4 v3, 0x4

    goto :goto_0

    :sswitch_3
    const-string/jumbo v1, "mainMenuLayout"

    invoke-virtual {p2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_4

    goto :goto_0

    :cond_4
    const/4 v3, 0x3

    goto :goto_0

    :sswitch_4
    const-string v1, "customSavePath"

    invoke-virtual {p2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_5

    goto :goto_0

    :cond_5
    const/4 v3, 0x2

    goto :goto_0

    :sswitch_5
    const-string/jumbo v1, "swipeActions"

    invoke-virtual {p2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_6

    goto :goto_0

    :cond_6
    move v3, v2

    goto :goto_0

    :sswitch_6
    const-string/jumbo v1, "selectedRole"

    invoke-virtual {p2, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_7

    goto :goto_0

    :cond_7
    move v3, v0

    :goto_0
    packed-switch v3, :pswitch_data_0

    .line 340
    const-string v0, "aiConfig"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_8

    const-string/jumbo v0, "roles"

    invoke-virtual {p2, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_8

    .line 341
    invoke-direct {p0, p3}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedRoles(Ljava/lang/String;)Z

    move-result p0

    return p0

    .line 343
    :cond_8
    const-string/jumbo v0, "pillstackconfig"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_a

    const-string p1, "activePills"

    .line 344
    invoke-virtual {p2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_9

    const-string/jumbo p1, "hiddenPills"

    invoke-virtual {p2, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_a

    .line 345
    :cond_9
    invoke-direct {p0, p3}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedPillsLayout(Ljava/lang/String;)Z

    move-result p0

    return p0

    .line 347
    :cond_a
    const-string/jumbo p0, "gramTargetCurrency"

    invoke-virtual {p2, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_c

    const-string p0, "btcTargetCurrency"

    .line 348
    invoke-virtual {p2, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_c

    const-string/jumbo p0, "usdTargetCurrency"

    .line 349
    invoke-virtual {p2, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_b

    goto :goto_1

    .line 352
    :cond_b
    invoke-static {p3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    xor-int/2addr p0, v2

    return p0

    .line 350
    :cond_c
    :goto_1
    const-string p0, "^[A-Z]{3,5}$"

    invoke-virtual {p3, p0}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result p0

    return p0

    .line 337
    :pswitch_0
    invoke-direct {p0, p3, v0}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedMainMenuLayout(Ljava/lang/String;Z)Z

    move-result p0

    return p0

    .line 321
    :pswitch_1
    const-string p0, "app"

    invoke-virtual {p3, p0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p0

    if-nez p0, :cond_e

    const-string p0, "^[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*$"

    .line 322
    invoke-virtual {p3, p0}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_d

    goto :goto_2

    :cond_d
    return v0

    :cond_e
    :goto_2
    return v2

    .line 334
    :pswitch_2
    invoke-direct {p0, p3, v2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedMainMenuLayout(Ljava/lang/String;Z)Z

    move-result p0

    return p0

    .line 328
    :pswitch_3
    const-string p0, "^(?!\\.{1,2}$)[A-Za-z0-9._ -]{1,255}$"

    invoke-virtual {p3, p0}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result p0

    return p0

    .line 325
    :pswitch_4
    invoke-virtual {p3}, Ljava/lang/String;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_10

    const-string p0, "^\\d{1,2}(,\\d{1,2})*$"

    invoke-virtual {p3, p0}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_f

    goto :goto_3

    :cond_f
    return v0

    :cond_10
    :goto_3
    return v2

    .line 331
    :pswitch_5
    invoke-static {p3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_11

    invoke-virtual {p3}, Ljava/lang/String;->length()I

    move-result p0

    const/16 p1, 0x100

    if-gt p0, p1, :cond_11

    return v2

    :cond_11
    :goto_4
    return v0

    nop

    :sswitch_data_0
    .sparse-switch
        -0x6893de4f -> :sswitch_6
        -0x2b04139d -> :sswitch_5
        -0x23a07e2d -> :sswitch_4
        -0x278f7fe -> :sswitch_3
        0x1cfd471f -> :sswitch_2
        0x40dde347 -> :sswitch_1
        0x63108e5e -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private isExpectedStringSet(Ljava/lang/String;Lcom/google/gson/JsonElement;)Z
    .locals 3

    .line 356
    const-string/jumbo p0, "pinnedPlugins"

    invoke-virtual {p1, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    const/4 p1, 0x0

    if-eqz p0, :cond_5

    invoke-virtual {p2}, Lcom/google/gson/JsonElement;->isJsonArray()Z

    move-result p0

    if-eqz p0, :cond_5

    invoke-virtual {p2}, Lcom/google/gson/JsonElement;->getAsJsonArray()Lcom/google/gson/JsonArray;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonArray;->size()I

    move-result p0

    const/16 v0, 0x3e8

    if-le p0, v0, :cond_0

    goto :goto_1

    .line 359
    :cond_0
    new-instance p0, Ljava/util/HashSet;

    invoke-direct {p0}, Ljava/util/HashSet;-><init>()V

    .line 360
    invoke-virtual {p2}, Lcom/google/gson/JsonElement;->getAsJsonArray()Lcom/google/gson/JsonArray;

    move-result-object p2

    invoke-virtual {p2}, Lcom/google/gson/JsonArray;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :cond_1
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/gson/JsonElement;

    .line 361
    invoke-virtual {v0}, Lcom/google/gson/JsonElement;->isJsonPrimitive()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-virtual {v0}, Lcom/google/gson/JsonElement;->getAsJsonPrimitive()Lcom/google/gson/JsonPrimitive;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/gson/JsonPrimitive;->isString()Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_0

    .line 364
    :cond_2
    invoke-virtual {v0}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object v0

    .line 365
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_3

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v1

    const/16 v2, 0xff

    if-gt v1, v2, :cond_3

    invoke-virtual {p0, v0}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    :cond_3
    :goto_0
    return p1

    :cond_4
    const/4 p0, 0x1

    return p0

    :cond_5
    :goto_1
    return p1
.end method

.method private isExpectedValue(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Z
    .locals 5

    .line 220
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->findBackupItem(Ljava/lang/String;Ljava/lang/String;)Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;

    move-result-object v0

    const/4 v1, 0x0

    if-eqz v0, :cond_b

    if-nez p3, :cond_0

    goto/16 :goto_2

    .line 225
    :cond_0
    instance-of v2, p3, Lcom/google/gson/JsonElement;

    if-eqz v2, :cond_1

    .line 226
    check-cast p3, Lcom/google/gson/JsonElement;

    goto :goto_0

    .line 227
    :cond_1
    iget-object v2, p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->gson:Lcom/google/gson/Gson;

    invoke-virtual {v2, p3}, Lcom/google/gson/Gson;->toJsonTree(Ljava/lang/Object;)Lcom/google/gson/JsonElement;

    move-result-object p3

    .line 230
    :goto_0
    :try_start_0
    iget-object v2, v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->clazz:Ljava/lang/Class;

    const-class v3, Ljava/lang/Boolean;

    invoke-virtual {v2, v3}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    const/4 v3, 0x1

    if-eqz v2, :cond_3

    .line 231
    invoke-virtual {p3}, Lcom/google/gson/JsonElement;->isJsonPrimitive()Z

    move-result p0

    if-eqz p0, :cond_2

    invoke-virtual {p3}, Lcom/google/gson/JsonElement;->getAsJsonPrimitive()Lcom/google/gson/JsonPrimitive;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonPrimitive;->isBoolean()Z

    move-result p0

    if-eqz p0, :cond_2

    return v3

    :catch_0
    move-exception p0

    goto/16 :goto_1

    :cond_2
    return v1

    .line 233
    :cond_3
    iget-object v2, v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->clazz:Ljava/lang/Class;

    const-class v4, Ljava/lang/Float;

    invoke-virtual {v2, v4}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_5

    .line 234
    invoke-virtual {p3}, Lcom/google/gson/JsonElement;->isJsonPrimitive()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 235
    invoke-virtual {p3}, Lcom/google/gson/JsonElement;->getAsJsonPrimitive()Lcom/google/gson/JsonPrimitive;

    move-result-object p1

    invoke-virtual {p1}, Lcom/google/gson/JsonPrimitive;->isNumber()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 236
    invoke-virtual {p3}, Lcom/google/gson/JsonElement;->getAsFloat()F

    move-result p1

    invoke-direct {p0, p2, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedFloat(Ljava/lang/String;F)Z

    move-result p0

    if-eqz p0, :cond_4

    return v3

    :cond_4
    return v1

    .line 238
    :cond_5
    iget-object v2, v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->clazz:Ljava/lang/Class;

    const-class v4, Ljava/lang/String;

    invoke-virtual {v2, v4}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_7

    .line 239
    invoke-virtual {p3}, Lcom/google/gson/JsonElement;->isJsonPrimitive()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 240
    invoke-virtual {p3}, Lcom/google/gson/JsonElement;->getAsJsonPrimitive()Lcom/google/gson/JsonPrimitive;

    move-result-object v0

    invoke-virtual {v0}, Lcom/google/gson/JsonPrimitive;->isString()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 241
    invoke-virtual {p3}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object p3

    invoke-direct {p0, p1, p2, p3}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedString(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_6

    return v3

    :cond_6
    return v1

    .line 243
    :cond_7
    iget-object p1, v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->clazz:Ljava/lang/Class;

    const-class v2, Ljava/util/Set;

    invoke-virtual {p1, v2}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_8

    .line 244
    invoke-direct {p0, p2, p3}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedStringSet(Ljava/lang/String;Lcom/google/gson/JsonElement;)Z

    move-result p0

    return p0

    .line 246
    :cond_8
    iget-object p1, v0, Lcom/exteragram/messenger/backup/PreferencesUtils$BackupItem;->clazz:Ljava/lang/Class;

    const-class v0, Ljava/lang/Long;

    invoke-virtual {p1, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_9

    return v1

    .line 250
    :cond_9
    invoke-direct {p0, p3}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getExactInteger(Lcom/google/gson/JsonElement;)Ljava/lang/Integer;

    move-result-object p1

    if-eqz p1, :cond_a

    .line 251
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-direct {p0, p2, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedInteger(Ljava/lang/String;I)Z

    move-result p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    if-eqz p0, :cond_a

    return v3

    :cond_a
    return v1

    .line 253
    :goto_1
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_b
    :goto_2
    return v1
.end method

.method private isRequiredString(Lcom/google/gson/JsonObject;Ljava/lang/String;I)Z
    .locals 0

    .line 424
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->has(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 425
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->isJsonNull()Z

    move-result p0

    if-nez p0, :cond_0

    .line 426
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->isJsonPrimitive()Z

    move-result p0

    if-eqz p0, :cond_0

    .line 427
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->getAsJsonPrimitive()Lcom/google/gson/JsonPrimitive;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonPrimitive;->isString()Z

    move-result p0

    if-eqz p0, :cond_0

    .line 428
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p0

    if-nez p0, :cond_0

    .line 429
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    if-gt p0, p3, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private readAndDecryptFile(Ljava/io/File;)Ljava/lang/String;
    .locals 1

    .line 606
    new-instance p0, Ljava/io/BufferedReader;

    new-instance v0, Ljava/io/FileReader;

    invoke-direct {v0, p1}, Ljava/io/FileReader;-><init>(Ljava/io/File;)V

    invoke-direct {p0, v0}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 607
    :try_start_0
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    .line 609
    :goto_0
    invoke-virtual {p0}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 610
    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 612
    :cond_0
    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 614
    invoke-static {p1}, Lcom/exteragram/messenger/backup/InvisibleEncryptor;->isEncrypted(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 615
    invoke-static {p1}, Lcom/exteragram/messenger/backup/InvisibleEncryptor;->decode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 619
    :cond_1
    invoke-virtual {p0}, Ljava/io/BufferedReader;->close()V

    return-object p1

    .line 606
    :goto_1
    :try_start_1
    invoke-virtual {p0}, Ljava/io/BufferedReader;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_2

    :catchall_1
    move-exception p0

    invoke-virtual {p1, p0}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :goto_2
    throw p1
.end method

.method private toJsonObject(Ljava/lang/String;Ljava/util/Map;)Lcom/google/gson/JsonObject;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "*>;)",
            "Lcom/google/gson/JsonObject;"
        }
    .end annotation

    .line 498
    new-instance v0, Lcom/google/gson/JsonObject;

    invoke-direct {v0}, Lcom/google/gson/JsonObject;-><init>()V

    .line 500
    invoke-interface {p2}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p2

    invoke-interface {p2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :cond_0
    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    .line 501
    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 502
    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    .line 503
    invoke-direct {p0, p1, v2, v1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedValue(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 504
    iget-object v3, p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->gson:Lcom/google/gson/Gson;

    invoke-virtual {v3, v1}, Lcom/google/gson/Gson;->toJsonTree(Ljava/lang/Object;)Lcom/google/gson/JsonElement;

    move-result-object v1

    .line 505
    invoke-virtual {v0, v2, v1}, Lcom/google/gson/JsonObject;->add(Ljava/lang/String;Lcom/google/gson/JsonElement;)V

    goto :goto_0

    :cond_1
    return-object v0
.end method


# virtual methods
.method public exportSettings(Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 20

    .line 451
    new-instance v0, Ljava/io/File;

    const/4 v1, 0x4

    invoke-static {v1}, Lorg/telegram/messenger/FileLoader;->getDirectory(I)Ljava/io/File;

    move-result-object v1

    const/4 v2, 0x0

    invoke-static {v2}, Lcom/exteragram/messenger/backup/PreferencesUtils;->generateBackupName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 453
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 454
    invoke-virtual {v0}, Ljava/io/File;->delete()Z

    .line 458
    :cond_0
    :try_start_0
    new-instance v1, Ljava/io/FileOutputStream;

    invoke-direct {v1, v0}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    .line 459
    new-instance v2, Ljava/io/OutputStreamWriter;

    sget-object v3, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v2, v1, v3}, Ljava/io/OutputStreamWriter;-><init>(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V

    const/4 v1, 0x1

    move-object/from16 v4, p0

    .line 460
    invoke-virtual {v4, v1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getBackup(Z)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v1}, Ljava/io/Writer;->write(Ljava/lang/String;)V

    .line 461
    invoke-virtual {v2}, Ljava/io/OutputStreamWriter;->flush()V

    .line 462
    invoke-virtual {v2}, Ljava/io/OutputStreamWriter;->close()V

    .line 464
    new-instance v3, Lcom/exteragram/messenger/backup/PreferencesUtils$1;

    invoke-virtual/range {p1 .. p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v5

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v8

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    move-object/from16 v19, p1

    invoke-direct/range {v3 .. v19}, Lcom/exteragram/messenger/backup/PreferencesUtils$1;-><init>(Lcom/exteragram/messenger/backup/PreferencesUtils;Landroid/content/Context;Lorg/telegram/ui/ChatActivity;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ZZZLjava/lang/Integer;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    move-object/from16 v0, p1

    invoke-virtual {v0, v3}, Lorg/telegram/ui/ActionBar/BaseFragment;->showDialog(Landroid/app/Dialog;)Landroid/app/Dialog;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    .line 473
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method public getBackup(Z)Ljava/lang/String;
    .locals 7

    .line 478
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->ensureConfigMigrated()V

    .line 479
    new-instance v0, Lcom/google/gson/JsonObject;

    invoke-direct {v0}, Lcom/google/gson/JsonObject;-><init>()V

    .line 481
    sget-object v1, Lcom/exteragram/messenger/backup/PreferencesUtils;->configs:[Ljava/lang/String;

    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_1

    aget-object v4, v1, v3

    .line 482
    invoke-static {v4}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getPreferences(Ljava/lang/String;)Landroid/content/SharedPreferences;

    move-result-object v5

    invoke-interface {v5}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v5

    invoke-direct {p0, v4, v5}, Lcom/exteragram/messenger/backup/PreferencesUtils;->toJsonObject(Ljava/lang/String;Ljava/util/Map;)Lcom/google/gson/JsonObject;

    move-result-object v5

    .line 483
    invoke-virtual {v5}, Lcom/google/gson/JsonObject;->isEmpty()Z

    move-result v6

    if-nez v6, :cond_0

    .line 484
    invoke-virtual {v0, v4, v5}, Lcom/google/gson/JsonObject;->add(Ljava/lang/String;Lcom/google/gson/JsonElement;)V

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 488
    :cond_1
    iget-object p0, p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->gson:Lcom/google/gson/Gson;

    invoke-virtual {p0, v0}, Lcom/google/gson/Gson;->toJson(Lcom/google/gson/JsonElement;)Ljava/lang/String;

    move-result-object p0

    if-eqz p1, :cond_2

    .line 491
    invoke-static {p0}, Lcom/exteragram/messenger/backup/InvisibleEncryptor;->encode(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    :cond_2
    return-object p0
.end method

.method public getDiff(Lcom/google/gson/JsonObject;)I
    .locals 11

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return v0

    .line 661
    :cond_0
    iget-object v1, p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->gson:Lcom/google/gson/Gson;

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getBackup(Z)Ljava/lang/String;

    move-result-object v2

    const-class v3, Lcom/google/gson/JsonObject;

    invoke-virtual {v1, v2, v3}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/gson/JsonObject;

    .line 663
    invoke-virtual {p1}, Lcom/google/gson/JsonObject;->keySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_1
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_8

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 664
    const-string v4, "^mainconfig\\d+$"

    invoke-virtual {v3, v4}, Ljava/lang/String;->matches(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_2

    .line 665
    const-string/jumbo v4, "mainconfig"

    goto :goto_1

    :cond_2
    move-object v4, v3

    .line 667
    :goto_1
    invoke-virtual {p1, v3}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v5

    invoke-virtual {v5}, Lcom/google/gson/JsonElement;->isJsonObject()Z

    move-result v5

    if-nez v5, :cond_3

    goto :goto_0

    .line 670
    :cond_3
    invoke-virtual {p1, v3}, Lcom/google/gson/JsonObject;->getAsJsonObject(Ljava/lang/String;)Lcom/google/gson/JsonObject;

    move-result-object v3

    .line 671
    invoke-virtual {v1, v4}, Lcom/google/gson/JsonObject;->has(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_6

    .line 672
    invoke-virtual {v1, v4}, Lcom/google/gson/JsonObject;->getAsJsonObject(Ljava/lang/String;)Lcom/google/gson/JsonObject;

    move-result-object v5

    .line 674
    invoke-virtual {v3}, Lcom/google/gson/JsonObject;->keySet()Ljava/util/Set;

    move-result-object v6

    invoke-interface {v6}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v6

    :cond_4
    :goto_2
    invoke-interface {v6}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_1

    invoke-interface {v6}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    .line 675
    invoke-virtual {v3, v7}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v8

    invoke-virtual {v5, v7}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v9

    .line 676
    invoke-virtual {v5, v7}, Lcom/google/gson/JsonObject;->has(Ljava/lang/String;)Z

    move-result v10

    if-eqz v10, :cond_5

    invoke-virtual {v8, v9}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-nez v9, :cond_4

    .line 677
    :cond_5
    invoke-direct {p0, v4, v7, v8}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedValue(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Z

    move-result v7

    if-eqz v7, :cond_4

    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 683
    :cond_6
    invoke-virtual {v3}, Lcom/google/gson/JsonObject;->keySet()Ljava/util/Set;

    move-result-object v5

    invoke-interface {v5}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :cond_7
    :goto_3
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_1

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    .line 684
    invoke-virtual {v3, v6}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object v7

    invoke-direct {p0, v4, v6, v7}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isExpectedValue(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_7

    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    :cond_8
    return v0
.end method

.method public getDiff(Ljava/io/File;)I
    .locals 0

    .line 653
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getJsonObject(Ljava/io/File;)Lcom/google/gson/JsonObject;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getDiff(Lcom/google/gson/JsonObject;)I

    move-result p0

    return p0
.end method

.method public getJsonObject(Ljava/io/File;)Lcom/google/gson/JsonObject;
    .locals 1

    .line 594
    :try_start_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->readAndDecryptFile(Ljava/io/File;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 597
    iget-object p0, p0, Lcom/exteragram/messenger/backup/PreferencesUtils;->gson:Lcom/google/gson/Gson;

    const-class v0, Lcom/google/gson/JsonObject;

    invoke-virtual {p0, p1, v0}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/gson/JsonObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 600
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public importSettings(Ljava/io/File;Landroid/app/Activity;Lorg/telegram/ui/ActionBar/INavigationLayout;)V
    .locals 5

    .line 513
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isBackup(Ljava/io/File;)Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    .line 517
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->ensureConfigMigrated()V

    .line 518
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getJsonObject(Ljava/io/File;)Lcom/google/gson/JsonObject;

    move-result-object p1

    .line 520
    sget-object v0, Lcom/exteragram/messenger/backup/PreferencesUtils;->configs:[Ljava/lang/String;

    array-length v1, v0

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_1

    aget-object v4, v0, v3

    .line 521
    invoke-direct {p0, p1, v4}, Lcom/exteragram/messenger/backup/PreferencesUtils;->importConfig(Lcom/google/gson/JsonObject;Ljava/lang/String;)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 524
    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->reloadConfig()V

    .line 525
    invoke-static {}, Lcom/exteragram/messenger/pillstack/core/PillStackConfig;->reloadConfig()V

    .line 526
    invoke-static {}, Lorg/telegram/messenger/SharedConfig;->reloadConfig()V

    const/4 p0, 0x0

    .line 527
    invoke-static {p0}, Lcom/exteragram/messenger/ai/network/backend/OnDeviceAvailability;->onModelConfigChanged(Ljava/lang/Runnable;)V

    .line 529
    invoke-static {}, Lcom/exteragram/messenger/plugins/PluginsController;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->restart()V

    .line 530
    invoke-static {}, Lorg/telegram/messenger/LocaleController;->getInstance()Lorg/telegram/messenger/LocaleController;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/LocaleController;->recreateFormatters()V

    .line 531
    invoke-static {p2}, Lorg/telegram/ui/ActionBar/Theme;->reloadAllResources(Landroid/content/Context;)V

    .line 534
    invoke-interface {p3, v2, v2}, Lorg/telegram/ui/ActionBar/INavigationLayout;->rebuildAllFragmentViews(ZZ)V

    .line 536
    sget p0, Lorg/telegram/messenger/UserConfig;->selectedAccount:I

    invoke-static {p0}, Lorg/telegram/messenger/AccountInstance;->getInstance(I)Lorg/telegram/messenger/AccountInstance;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/messenger/AccountInstance;->getNotificationCenter()Lorg/telegram/messenger/NotificationCenter;

    move-result-object p0

    .line 537
    sget p1, Lorg/telegram/messenger/NotificationCenter;->reloadInterface:I

    new-array p2, v2, [Ljava/lang/Object;

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 538
    sget p1, Lorg/telegram/messenger/NotificationCenter;->updateInterfaces:I

    sget p2, Lorg/telegram/messenger/MessagesController;->UPDATE_MASK_CHAT:I

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 539
    sget p1, Lorg/telegram/messenger/NotificationCenter;->mainUserInfoChanged:I

    new-array p2, v2, [Ljava/lang/Object;

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 540
    sget p1, Lorg/telegram/messenger/NotificationCenter;->dialogFiltersUpdated:I

    new-array p2, v2, [Ljava/lang/Object;

    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method public isBackup(Ljava/io/File;)Z
    .locals 3

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    .line 645
    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v1

    const-string v2, ".extera"

    invoke-virtual {v1, v2}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 648
    :cond_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->getJsonObject(Ljava/io/File;)Lcom/google/gson/JsonObject;

    move-result-object p1

    if-eqz p1, :cond_1

    .line 649
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->checkKeys(Lcom/google/gson/JsonObject;)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    :goto_0
    return v0
.end method

.method public isBackup(Lorg/telegram/messenger/MessageObject;)Z
    .locals 1

    .line 640
    invoke-static {}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getInstance()Lcom/exteragram/messenger/utils/chats/ChatUtils;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/utils/chats/ChatUtils;->getPathToMessage(Lorg/telegram/messenger/MessageObject;)Ljava/lang/String;

    move-result-object v0

    if-eqz p1, :cond_0

    .line 641
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDocumentName()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_0

    new-instance p1, Ljava/io/File;

    invoke-direct {p1, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/backup/PreferencesUtils;->isBackup(Ljava/io/File;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
