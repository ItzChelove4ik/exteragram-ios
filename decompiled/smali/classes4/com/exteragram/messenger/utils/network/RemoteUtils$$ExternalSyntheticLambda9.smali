.class public final synthetic Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda9;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/messenger/AccountInstance;

.field public final synthetic f$1:Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;

.field public final synthetic f$2:I


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/messenger/AccountInstance;Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;I)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda9;->f$0:Lorg/telegram/messenger/AccountInstance;

    iput-object p2, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda9;->f$1:Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;

    iput p3, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda9;->f$2:I

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda9;->f$0:Lorg/telegram/messenger/AccountInstance;

    iget-object v1, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda9;->f$1:Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;

    iget p0, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda9;->f$2:I

    invoke-static {v0, v1, p0}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->$r8$lambda$E3fegTDk-h-tibGgJJ4Zwo5Ymw0(Lorg/telegram/messenger/AccountInstance;Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;I)V

    return-void
.end method
