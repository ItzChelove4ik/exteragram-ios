.class Lcom/exteragram/messenger/ai/TelegramAiReplacement$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/ai/network/GenerationCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/ai/TelegramAiReplacement;->run(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;Lorg/telegram/messenger/Utilities$Callback2;)Ljava/lang/Runnable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic val$onError:Lorg/telegram/messenger/Utilities$Callback2;

.field final synthetic val$onResult:Lorg/telegram/messenger/Utilities$Callback;

.field final synthetic val$tag:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;Lorg/telegram/messenger/Utilities$Callback2;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 97
    iput-object p1, p0, Lcom/exteragram/messenger/ai/TelegramAiReplacement$1;->val$tag:Ljava/lang/String;

    iput-object p2, p0, Lcom/exteragram/messenger/ai/TelegramAiReplacement$1;->val$onResult:Lorg/telegram/messenger/Utilities$Callback;

    iput-object p3, p0, Lcom/exteragram/messenger/ai/TelegramAiReplacement$1;->val$onError:Lorg/telegram/messenger/Utilities$Callback2;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onChunk(Ljava/lang/String;)V
    .locals 0

    return-void
.end method

.method public onError(ILjava/lang/String;)V
    .locals 0

    .line 111
    iget-object p0, p0, Lcom/exteragram/messenger/ai/TelegramAiReplacement$1;->val$onError:Lorg/telegram/messenger/Utilities$Callback2;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-interface {p0, p1, p2}, Lorg/telegram/messenger/Utilities$Callback2;->run(Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method

.method public onResponse(Ljava/lang/String;)V
    .locals 2

    .line 104
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;-><init>()V

    .line 105
    iget-object v1, p0, Lcom/exteragram/messenger/ai/TelegramAiReplacement$1;->val$tag:Ljava/lang/String;

    invoke-static {p1, v1}, Lcom/exteragram/messenger/ai/TelegramAiReplacement;->-$$Nest$smcleanResponse(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, v0, Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;->text:Ljava/lang/String;

    .line 106
    iget-object p0, p0, Lcom/exteragram/messenger/ai/TelegramAiReplacement$1;->val$onResult:Lorg/telegram/messenger/Utilities$Callback;

    invoke-interface {p0, v0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void
.end method
