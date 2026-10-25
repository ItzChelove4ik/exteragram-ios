.class public final synthetic Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda6;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/core/util/Consumer;


# instance fields
.field public final synthetic f$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

.field public final synthetic f$1:Lorg/telegram/tgnet/TLRPC$Chat;


# direct methods
.method public synthetic constructor <init>(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Lorg/telegram/tgnet/TLRPC$Chat;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda6;->f$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    iput-object p2, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda6;->f$1:Lorg/telegram/tgnet/TLRPC$Chat;

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 1

    .line 0
    iget-object v0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda6;->f$0:Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;

    iget-object p0, p0, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity$$ExternalSyntheticLambda6;->f$1:Lorg/telegram/tgnet/TLRPC$Chat;

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    invoke-static {v0, p0, p1}, Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;->$r8$lambda$jwCrvnnUGaVtUIzGRQQdgmPoqhM(Lcom/exteragram/messenger/feed/ui/FeedChannelsActivity;Lorg/telegram/tgnet/TLRPC$Chat;Z)V

    return-void
.end method
