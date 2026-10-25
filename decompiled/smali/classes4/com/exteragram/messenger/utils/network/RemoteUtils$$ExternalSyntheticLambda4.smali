.class public final synthetic Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/tgnet/TLRPC$TL_messages_search;

.field public final synthetic f$1:Lorg/telegram/messenger/AccountInstance;

.field public final synthetic f$2:Ljava/lang/Runnable;

.field public final synthetic f$3:Ljava/util/concurrent/atomic/AtomicReference;

.field public final synthetic f$4:I

.field public final synthetic f$5:Ljava/util/concurrent/atomic/AtomicBoolean;

.field public final synthetic f$6:Lorg/telegram/messenger/Utilities$Callback2;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/tgnet/TLRPC$TL_messages_search;Lorg/telegram/messenger/AccountInstance;Ljava/lang/Runnable;Ljava/util/concurrent/atomic/AtomicReference;ILjava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/Utilities$Callback2;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/tgnet/TLRPC$TL_messages_search;

    iput-object p2, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$1:Lorg/telegram/messenger/AccountInstance;

    iput-object p3, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$2:Ljava/lang/Runnable;

    iput-object p4, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$3:Ljava/util/concurrent/atomic/AtomicReference;

    iput p5, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$4:I

    iput-object p6, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$5:Ljava/util/concurrent/atomic/AtomicBoolean;

    iput-object p7, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$6:Lorg/telegram/messenger/Utilities$Callback2;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 7

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$0:Lorg/telegram/tgnet/TLRPC$TL_messages_search;

    iget-object v1, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$1:Lorg/telegram/messenger/AccountInstance;

    iget-object v2, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$2:Ljava/lang/Runnable;

    iget-object v3, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$3:Ljava/util/concurrent/atomic/AtomicReference;

    iget v4, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$4:I

    iget-object v5, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$5:Ljava/util/concurrent/atomic/AtomicBoolean;

    iget-object v6, p0, Lcom/exteragram/messenger/utils/network/RemoteUtils$$ExternalSyntheticLambda4;->f$6:Lorg/telegram/messenger/Utilities$Callback2;

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/utils/network/RemoteUtils;->$r8$lambda$Q2nxzL4QXD-hawk8oMmFpxL7pOE(Lorg/telegram/tgnet/TLRPC$TL_messages_search;Lorg/telegram/messenger/AccountInstance;Ljava/lang/Runnable;Ljava/util/concurrent/atomic/AtomicReference;ILjava/util/concurrent/atomic/AtomicBoolean;Lorg/telegram/messenger/Utilities$Callback2;)V

    return-void
.end method
