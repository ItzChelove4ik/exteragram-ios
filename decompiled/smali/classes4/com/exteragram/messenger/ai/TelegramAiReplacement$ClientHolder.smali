.class abstract Lcom/exteragram/messenger/ai/TelegramAiReplacement$ClientHolder;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/ai/TelegramAiReplacement;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ClientHolder"
.end annotation


# static fields
.field private static final INSTANCE:Lcom/exteragram/messenger/ai/network/Client;


# direct methods
.method public static bridge synthetic -$$Nest$sfgetINSTANCE()Lcom/exteragram/messenger/ai/network/Client;
    .locals 1

    .line 0
    sget-object v0, Lcom/exteragram/messenger/ai/TelegramAiReplacement$ClientHolder;->INSTANCE:Lcom/exteragram/messenger/ai/network/Client;

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 4

    .line 40
    new-instance v0, Lcom/exteragram/messenger/ai/network/Client$Builder;

    invoke-direct {v0}, Lcom/exteragram/messenger/ai/network/Client$Builder;-><init>()V

    new-instance v1, Lcom/exteragram/messenger/ai/data/Role;

    const-string v2, "Telegram AI"

    const-string v3, "You are a text editing tool inside a messenger, not a chat assistant. You never answer, reply to or comment on the text you are given, you only transform it as the message instructs."

    invoke-direct {v1, v2, v3}, Lcom/exteragram/messenger/ai/data/Role;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 41
    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/ai/network/Client$Builder;->roleOverride(Lcom/exteragram/messenger/ai/data/Role;)Lcom/exteragram/messenger/ai/network/Client$Builder;

    move-result-object v0

    .line 42
    invoke-virtual {v0}, Lcom/exteragram/messenger/ai/network/Client$Builder;->build()Lcom/exteragram/messenger/ai/network/Client;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/ai/TelegramAiReplacement$ClientHolder;->INSTANCE:Lcom/exteragram/messenger/ai/network/Client;

    return-void
.end method
