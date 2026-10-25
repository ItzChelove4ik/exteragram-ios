.class public final synthetic Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;

.field public final synthetic f$1:Lorg/telegram/messenger/AccountInstance;

.field public final synthetic f$2:Ljava/lang/Runnable;

.field public final synthetic f$3:I


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;Lorg/telegram/messenger/AccountInstance;Ljava/lang/Runnable;I)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;->f$0:Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;

    iput-object p2, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;->f$1:Lorg/telegram/messenger/AccountInstance;

    iput-object p3, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;->f$2:Ljava/lang/Runnable;

    iput p4, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;->f$3:I

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;->f$0:Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;

    iget-object v1, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;->f$1:Lorg/telegram/messenger/AccountInstance;

    iget-object v2, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;->f$2:Ljava/lang/Runnable;

    iget p0, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda10;->f$3:I

    invoke-static {v0, v1, v2, p0}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->$r8$lambda$UEIegV2IjuUzBjubE_kaeXJS8OE(Lorg/telegram/tgnet/TLRPC$TL_messages_getHistory;Lorg/telegram/messenger/AccountInstance;Ljava/lang/Runnable;I)V

    return-void
.end method
