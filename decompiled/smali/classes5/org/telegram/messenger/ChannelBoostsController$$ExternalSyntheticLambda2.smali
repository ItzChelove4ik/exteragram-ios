.class public final synthetic Lorg/telegram/messenger/ChannelBoostsController$$ExternalSyntheticLambda2;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/messenger/Utilities$Callback;


# instance fields
.field public final synthetic f$0:Lorg/telegram/messenger/ChannelBoostsController$CanApplyBoost;

.field public final synthetic f$1:Lorg/telegram/messenger/Utilities$Callback;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/messenger/ChannelBoostsController$CanApplyBoost;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/ChannelBoostsController$$ExternalSyntheticLambda2;->f$0:Lorg/telegram/messenger/ChannelBoostsController$CanApplyBoost;

    iput-object p2, p0, Lorg/telegram/messenger/ChannelBoostsController$$ExternalSyntheticLambda2;->f$1:Lorg/telegram/messenger/Utilities$Callback;

    return-void
.end method


# virtual methods
.method public final run(Ljava/lang/Object;)V
    .locals 1

    .line 0
    iget-object v0, p0, Lorg/telegram/messenger/ChannelBoostsController$$ExternalSyntheticLambda2;->f$0:Lorg/telegram/messenger/ChannelBoostsController$CanApplyBoost;

    iget-object p0, p0, Lorg/telegram/messenger/ChannelBoostsController$$ExternalSyntheticLambda2;->f$1:Lorg/telegram/messenger/Utilities$Callback;

    check-cast p1, Lorg/telegram/tgnet/TLRPC$TL_error;

    invoke-static {v0, p0, p1}, Lorg/telegram/messenger/ChannelBoostsController;->$r8$lambda$jS7ipboGIzjP5Pq2w1Aoiw_q3VM(Lorg/telegram/messenger/ChannelBoostsController$CanApplyBoost;Lorg/telegram/messenger/Utilities$Callback;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    return-void
.end method
