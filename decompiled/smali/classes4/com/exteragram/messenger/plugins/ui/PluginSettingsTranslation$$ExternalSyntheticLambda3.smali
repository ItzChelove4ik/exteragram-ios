.class public final synthetic Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/translator/ChatTranslator$Callback;


# instance fields
.field public final synthetic f$0:Ljava/util/List;

.field public final synthetic f$1:Lkotlin/jvm/functions/Function0;

.field public final synthetic f$2:[Ljava/lang/String;


# direct methods
.method public synthetic constructor <init>(Ljava/util/List;Lkotlin/jvm/functions/Function0;[Ljava/lang/String;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda3;->f$0:Ljava/util/List;

    iput-object p2, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda3;->f$1:Lkotlin/jvm/functions/Function0;

    iput-object p3, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda3;->f$2:[Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final onResult(Ljava/util/List;Lcom/exteragram/messenger/translator/core/TranslationError;)V
    .locals 2

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda3;->f$0:Ljava/util/List;

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda3;->f$1:Lkotlin/jvm/functions/Function0;

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$$ExternalSyntheticLambda3;->f$2:[Ljava/lang/String;

    invoke-static {v0, v1, p0, p1, p2}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;->$r8$lambda$BhislwJ_w_bX3GhCIz9I8vwOc8g(Ljava/util/List;Lkotlin/jvm/functions/Function0;[Ljava/lang/String;Ljava/util/List;Lcom/exteragram/messenger/translator/core/TranslationError;)V

    return-void
.end method
