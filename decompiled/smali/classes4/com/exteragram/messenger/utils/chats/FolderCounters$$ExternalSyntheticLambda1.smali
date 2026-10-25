.class public final synthetic Lcom/exteragram/messenger/utils/chats/FolderCounters$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/utils/chats/FolderCounters;

.field public final synthetic f$1:Lorg/telegram/messenger/MessagesStorage;


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/utils/chats/FolderCounters;Lorg/telegram/messenger/MessagesStorage;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$$ExternalSyntheticLambda1;->f$0:Lcom/exteragram/messenger/utils/chats/FolderCounters;

    iput-object p2, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$$ExternalSyntheticLambda1;->f$1:Lorg/telegram/messenger/MessagesStorage;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$$ExternalSyntheticLambda1;->f$0:Lcom/exteragram/messenger/utils/chats/FolderCounters;

    iget-object p0, p0, Lcom/exteragram/messenger/utils/chats/FolderCounters$$ExternalSyntheticLambda1;->f$1:Lorg/telegram/messenger/MessagesStorage;

    invoke-static {v0, p0}, Lcom/exteragram/messenger/utils/chats/FolderCounters;->$r8$lambda$a7yR4wl_zPymeOD2mbHLE9R84bk(Lcom/exteragram/messenger/utils/chats/FolderCounters;Lorg/telegram/messenger/MessagesStorage;)V

    return-void
.end method
