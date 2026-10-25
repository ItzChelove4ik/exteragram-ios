.class public final Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;
.super Lcom/exteragram/messenger/preferences/BasePreferencesActivity;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;,
        Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$WhenMappings;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0007\n\u0002\u0008\u0002\n\u0002\u0010\r\n\u0002\u0008\u0006\u0018\u00002\u00020\u0001:\u0001\u001eB\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u0004\u001a\u00020\u0005H\u0016J\u0008\u0010\u0006\u001a\u00020\u0007H\u0014J\u001e\u0010\u0008\u001a\u00020\t2\u000c\u0010\n\u001a\u0008\u0012\u0004\u0012\u00020\u000c0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0014J0\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000c2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0014J\u0008\u0010\u0018\u001a\u00020\u0019H\u0002J\u0008\u0010\u001a\u001a\u00020\u000cH\u0002J\u0010\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0014H\u0002J\u0008\u0010\u001d\u001a\u00020\u0005H\u0002\u00a8\u0006\u001f"
    }
    d2 = {
        "Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;",
        "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;",
        "<init>",
        "()V",
        "getTitle",
        "",
        "needHideTitle",
        "",
        "fillItems",
        "",
        "items",
        "Ljava/util/ArrayList;",
        "Lorg/telegram/ui/Components/UItem;",
        "adapter",
        "Lorg/telegram/ui/Components/UniversalAdapter;",
        "onClick",
        "item",
        "view",
        "Landroid/view/View;",
        "position",
        "",
        "x",
        "",
        "y",
        "getHistoryValue",
        "",
        "createTemperatureSliderItem",
        "formatTemperature",
        "value",
        "getEndpointValue",
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


# direct methods
.method public static $r8$lambda$6tbZ2h-zPWYoXcUbJaDq4t3v0s0(Z)V
    .locals 0

    .line 104
    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setInsertAsQuote(Z)V

    return-void
.end method

.method public static $r8$lambda$MEeAGcO-LHjFdBVYKqgO4RBWzjU(Z)V
    .locals 0

    .line 102
    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setResponseStreaming(Z)V

    return-void
.end method

.method public static $r8$lambda$U1W9kDS-YFv1-OHo29RqURfUPCQ(Z)V
    .locals 0

    .line 106
    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setReplaceTelegramSummaries(Z)V

    return-void
.end method

.method public static synthetic $r8$lambda$bqyY2-5q31Vei_eltdFFjHL042A(Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;I)Ljava/lang/CharSequence;
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;->formatTemperature(I)Ljava/lang/CharSequence;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$lTV6ilwpipr7r3e4uylWI7OO4d4(Z)V
    .locals 0

    .line 105
    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setReplaceTelegramEditor(Z)V

    return-void
.end method

.method public static $r8$lambda$onHHKwH_Ba-sJsDEm0bE2Vp9bMA(Z)V
    .locals 0

    .line 103
    invoke-static {p0}, Lcom/exteragram/messenger/ai/AiConfig;->setShowResponseOnly(Z)V

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 40
    invoke-direct {p0}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;-><init>()V

    return-void
.end method

.method private final createTemperatureSliderItem()Lorg/telegram/ui/Components/UItem;
    .locals 6

    .line 118
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getTemperature()I

    move-result v2

    new-instance v4, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda0;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda0;-><init>(Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;)V

    sget-object p0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$createTemperatureSliderItem$3;->INSTANCE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$createTemperatureSliderItem$3;

    new-instance v5, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda1;

    invoke-direct {v5, p0}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda1;-><init>(Lkotlin/reflect/KMutableProperty0;)V

    const/4 v0, 0x1

    const/4 v1, 0x0

    const/16 v3, 0x14

    invoke-static/range {v0 .. v5}, Lorg/telegram/ui/Components/UItem;->asIntSlideView(IIIILorg/telegram/messenger/Utilities$CallbackReturn;Lorg/telegram/messenger/Utilities$Callback;)Lorg/telegram/ui/Components/UItem;

    move-result-object p0

    .line 119
    sget-object v0, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->TEMPERATURE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->getId()I

    move-result v0

    iput v0, p0, Lorg/telegram/ui/Components/UItem;->id:I

    .line 120
    sget v0, Lorg/telegram/messenger/R$string;->AITemperature:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/ui/Components/UItem;->text:Ljava/lang/CharSequence;

    return-object p0
.end method

.method private final formatTemperature(I)Ljava/lang/CharSequence;
    .locals 1

    .line 124
    sget-object p0, Lkotlin/jvm/internal/StringCompanionObject;->INSTANCE:Lkotlin/jvm/internal/StringCompanionObject;

    sget-object p0, Ljava/util/Locale;->US:Ljava/util/Locale;

    int-to-float p1, p1

    const/high16 v0, 0x41200000    # 10.0f

    div-float/2addr p1, v0

    invoke-static {p1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p1

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    const/4 v0, 0x1

    invoke-static {p1, v0}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object p1

    const-string v0, "%.1f"

    invoke-static {p0, v0, p1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final getEndpointValue()Ljava/lang/String;
    .locals 4

    .line 127
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/AiController;->getSelected()Lcom/exteragram/messenger/ai/data/Service;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/ai/data/Service;->isOnDevice()Z

    move-result p0

    if-eqz p0, :cond_1

    .line 128
    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->canUseAI()Z

    move-result p0

    if-eqz p0, :cond_0

    sget p0, Lorg/telegram/messenger/R$string;->AIOnDevice:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_0
    sget p0, Lorg/telegram/messenger/R$string;->BlockedEmpty:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 131
    :cond_1
    :try_start_0
    new-instance p0, Ljava/net/URL;

    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->getInstance()Lcom/exteragram/messenger/ai/AiController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/AiController;->getSelected()Lcom/exteragram/messenger/ai/data/Service;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/data/Service;->getUrl()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0}, Ljava/net/URL;->getHost()Ljava/lang/String;

    move-result-object p0

    .line 132
    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_4

    invoke-static {}, Lcom/exteragram/messenger/ai/AiController;->canUseAI()Z

    move-result v0

    if-nez v0, :cond_2

    goto :goto_0

    .line 134
    :cond_2
    const-string v0, "generativelanguage.googleapis"

    const/4 v1, 0x2

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static {p0, v0, v3, v1, v2}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 135
    const-string p0, "Gemini"

    :cond_3
    return-object p0

    .line 133
    :cond_4
    :goto_0
    sget p0, Lorg/telegram/messenger/R$string;->BlockedEmpty:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/net/MalformedURLException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 140
    :catch_0
    sget p0, Lorg/telegram/messenger/R$string;->BlockedEmpty:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final getHistoryValue()Ljava/lang/CharSequence;
    .locals 2

    .line 112
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSaveHistory()Z

    move-result p0

    if-nez p0, :cond_0

    sget p0, Lorg/telegram/messenger/R$string;->BlurOff:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 113
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getConversationHistory()Ljava/util/ArrayList;

    move-result-object p0

    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result p0

    if-nez p0, :cond_1

    .line 114
    sget p0, Lorg/telegram/messenger/R$string;->BlockedEmpty:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    const/4 v0, 0x0

    new-array v0, v0, [Ljava/lang/Object;

    const-string v1, "messages"

    invoke-static {v1, p0, v0}, Lorg/telegram/messenger/LocaleController;->formatPluralString(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method


# virtual methods
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

    move-object/from16 v14, p1

    .line 61
    invoke-virtual/range {p0 .. p0}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;->getTitle()Ljava/lang/String;

    move-result-object v0

    sget v1, Lorg/telegram/messenger/R$string;->AIChatInfo2:I

    invoke-static {v1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v1

    const-string v2, "exteraGramPlaceholders"

    const-string v3, "\ud83e\udd16"

    invoke-static {v0, v1, v2, v3}, Lorg/telegram/ui/Components/UItem;->asTopView(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/String;Ljava/lang/String;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 62
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 64
    sget v0, Lorg/telegram/messenger/R$string;->General:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 65
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->ENDPOINT:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget v0, Lorg/telegram/messenger/R$string;->Services:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_language:I

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;->getEndpointValue()Ljava/lang/String;

    move-result-object v4

    sget-object v11, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    const/16 v12, 0x370

    const/4 v13, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const-string v8, "aiServices"

    const/4 v9, 0x0

    const/4 v10, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 66
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->ROLE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget v0, Lorg/telegram/messenger/R$string;->Roles:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_openprofile:I

    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getSelectedRole()Ljava/lang/String;

    move-result-object v4

    const-string v8, "aiRoles"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 67
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->HISTORY:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget v0, Lorg/telegram/messenger/R$string;->MessageHistory:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    sget v3, Lorg/telegram/messenger/R$drawable;->msg_discuss:I

    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;->getHistoryValue()Ljava/lang/CharSequence;

    move-result-object v4

    const-string v8, "aiHistory"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v13}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uButton$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Boolean;ZLjava/lang/String;ZZLjava/lang/Boolean;ILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 68
    sget v0, Lorg/telegram/messenger/R$string;->HistoryInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 70
    sget v0, Lorg/telegram/messenger/R$string;->AIReplaceTelegram:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 71
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->REPLACE_TELEGRAM_EDITOR:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget v0, Lorg/telegram/messenger/R$string;->AIFeaturesEditor:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    const/4 v12, 0x0

    const/4 v13, 0x1

    const/4 v15, 0x0

    invoke-static {v12, v13, v15}, Lcom/exteragram/messenger/ai/AiConfig;->getReplaceTelegramEditor$default(IILjava/lang/Object;)Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->AIReplaceTelegramEditorInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/16 v10, 0x1b0

    const/4 v11, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const-string v7, "replaceTelegramEditor"

    const/4 v8, 0x0

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 72
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->REPLACE_TELEGRAM_SUMMARIES:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget v0, Lorg/telegram/messenger/R$string;->AIFeaturesSummaries:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {v12, v13, v15}, Lcom/exteragram/messenger/ai/AiConfig;->getReplaceTelegramSummaries$default(IILjava/lang/Object;)Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->AIReplaceTelegramSummariesInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const-string v7, "replaceTelegramSummaries"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v1, v12}, Lorg/telegram/ui/Components/UItem;->showDivider(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 73
    sget v0, Lorg/telegram/messenger/R$string;->AIReplaceTelegramInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 75
    sget v0, Lorg/telegram/messenger/R$string;->AIGeneration:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 76
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->RESPONSE_STREAMING:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget v0, Lorg/telegram/messenger/R$string;->ResponseStreaming:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getResponseStreaming()Z

    move-result v3

    sget v0, Lorg/telegram/messenger/R$string;->ResponseStreamingInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    const-string v7, "responseStreaming"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 77
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->SHOW_RESPONSE_ONLY:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget v0, Lorg/telegram/messenger/R$string;->ShowResponseOnly:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getShowResponseOnly()Z

    move-result v3

    const/16 v10, 0x1b8

    const/4 v4, 0x0

    const-string v7, "showResponseOnly"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 78
    sget-object v1, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->INSERT_AS_QUOTE:Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    sget v0, Lorg/telegram/messenger/R$string;->InsertResponseAsQuote:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {}, Lcom/exteragram/messenger/ai/AiConfig;->getInsertAsQuote()Z

    move-result v3

    const-string v7, "insertResponseAsQuote"

    move-object/from16 v0, p0

    invoke-static/range {v0 .. v11}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->uCheck$TMessagesProj$default(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;Ljava/lang/CharSequence;ZLjava/lang/CharSequence;IZLjava/lang/String;ZIILjava/lang/Object;)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v1, v12}, Lorg/telegram/ui/Components/UItem;->showDivider(Z)Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 79
    invoke-static {}, Lorg/telegram/ui/Components/UItem;->asShadow()Lorg/telegram/ui/Components/UItem;

    move-result-object v1

    invoke-virtual {v14, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 81
    const-string v1, "aiTemperature"

    invoke-static {v1}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->markAsNewFeature(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 82
    sget v2, Lorg/telegram/messenger/R$string;->AITemperature:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->applyNewSpan(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v2

    goto :goto_0

    .line 84
    :cond_0
    sget v2, Lorg/telegram/messenger/R$string;->AITemperature:I

    invoke-static {v2}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 86
    :goto_0
    invoke-static {v2}, Lorg/telegram/ui/Components/UItem;->asHeader(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    invoke-virtual {v14, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 87
    invoke-direct {v0}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity;->createTemperatureSliderItem()Lorg/telegram/ui/Components/UItem;

    move-result-object v2

    .line 88
    invoke-virtual {v2, v12}, Lorg/telegram/ui/Components/UItem;->showDivider(Z)Lorg/telegram/ui/Components/UItem;

    .line 89
    invoke-virtual {v2, v0}, Lorg/telegram/ui/Components/UItem;->setSearchable(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    .line 90
    invoke-virtual {v2, v1, v0}, Lorg/telegram/ui/Components/UItem;->setLinkAlias(Ljava/lang/String;Lcom/exteragram/messenger/preferences/BasePreferencesActivity;)Lorg/telegram/ui/Components/UItem;

    .line 87
    invoke-virtual {v14, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 92
    sget v0, Lorg/telegram/messenger/R$string;->AITemperatureInfo:I

    invoke-static {v0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/ui/Components/UItem;->asShadow(Ljava/lang/CharSequence;)Lorg/telegram/ui/Components/UItem;

    move-result-object v0

    invoke-virtual {v14, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method public getTitle()Ljava/lang/String;
    .locals 0

    .line 56
    sget p0, Lorg/telegram/messenger/R$string;->AIChat:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public needHideTitle()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public onClick(Lorg/telegram/ui/Components/UItem;Landroid/view/View;IFF)V
    .locals 0

    .line 96
    iget p2, p1, Lorg/telegram/ui/Components/UItem;->id:I

    if-lez p2, :cond_1

    invoke-static {}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p3

    invoke-interface {p3}, Ljava/util/List;->size()I

    move-result p3

    if-le p2, p3, :cond_0

    goto :goto_0

    .line 97
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;->getEntries()Lkotlin/enums/EnumEntries;

    move-result-object p2

    iget p3, p1, Lorg/telegram/ui/Components/UItem;->id:I

    add-int/lit8 p3, p3, -0x1

    invoke-interface {p2, p3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$PreferenceItem;

    .line 98
    sget-object p3, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$WhenMappings;->$EnumSwitchMapping$0:[I

    invoke-virtual {p2}, Ljava/lang/Enum;->ordinal()I

    move-result p2

    aget p2, p3, p2

    packed-switch p2, :pswitch_data_0

    goto :goto_0

    .line 106
    :pswitch_0
    new-instance p2, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda6;

    invoke-direct {p2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda6;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 105
    :pswitch_1
    new-instance p2, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda5;

    invoke-direct {p2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda5;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 104
    :pswitch_2
    new-instance p2, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda4;

    invoke-direct {p2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda4;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 103
    :pswitch_3
    new-instance p2, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda3;

    invoke-direct {p2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda3;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 102
    :pswitch_4
    new-instance p2, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda2;

    invoke-direct {p2}, Lcom/exteragram/messenger/ai/ui/activities/AiPreferencesActivity$$ExternalSyntheticLambda2;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/preferences/BasePreferencesActivity;->toggleBooleanSettingAndRefresh$TMessagesProj(Lorg/telegram/ui/Components/UItem;Landroidx/core/util/Consumer;)V

    return-void

    .line 101
    :pswitch_5
    new-instance p1, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/ai/ui/activities/AiHistoryActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 100
    :pswitch_6
    new-instance p1, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/ai/ui/activities/RolesActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void

    .line 99
    :pswitch_7
    new-instance p1, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;

    invoke-direct {p1}, Lcom/exteragram/messenger/ai/ui/activities/ServicesActivity;-><init>()V

    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    :cond_1
    :goto_0
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
