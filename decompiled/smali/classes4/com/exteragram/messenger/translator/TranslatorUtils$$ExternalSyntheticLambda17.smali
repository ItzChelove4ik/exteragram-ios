.class public final synthetic Lcom/exteragram/messenger/translator/TranslatorUtils$$ExternalSyntheticLambda17;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/LanguageDetector$StringCallback;


# instance fields
.field public final synthetic f$0:Ljava/lang/String;

.field public final synthetic f$1:Lorg/telegram/messenger/Utilities$Callback;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/translator/TranslatorUtils$$ExternalSyntheticLambda17;->f$0:Ljava/lang/String;

    iput-object p2, p0, Lcom/exteragram/messenger/translator/TranslatorUtils$$ExternalSyntheticLambda17;->f$1:Lorg/telegram/messenger/Utilities$Callback;

    return-void
.end method


# virtual methods
.method public final run(Ljava/lang/String;)V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/translator/TranslatorUtils$$ExternalSyntheticLambda17;->f$0:Ljava/lang/String;

    iget-object p0, p0, Lcom/exteragram/messenger/translator/TranslatorUtils$$ExternalSyntheticLambda17;->f$1:Lorg/telegram/messenger/Utilities$Callback;

    invoke-static {v0, p0, p1}, Lcom/exteragram/messenger/translator/TranslatorUtils;->$r8$lambda$2xKhWwpI4GW7E2vW__OfQr9ITvc(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;Ljava/lang/String;)V

    return-void
.end method
