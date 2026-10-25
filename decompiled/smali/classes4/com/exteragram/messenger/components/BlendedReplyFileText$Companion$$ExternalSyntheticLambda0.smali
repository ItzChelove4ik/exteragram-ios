.class public final synthetic Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function0;


# instance fields
.field public final synthetic f$0:Lorg/telegram/messenger/MessageObject;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/messenger/MessageObject;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion$$ExternalSyntheticLambda0;->f$0:Lorg/telegram/messenger/MessageObject;

    return-void
.end method


# virtual methods
.method public final invoke()Ljava/lang/Object;
    .locals 0

    .line 0
    iget-object p0, p0, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion$$ExternalSyntheticLambda0;->f$0:Lorg/telegram/messenger/MessageObject;

    invoke-static {p0}, Lcom/exteragram/messenger/components/BlendedReplyFileText$Companion;->$r8$lambda$q50XyqiFF0WlGV82xdVe1wcVvn4(Lorg/telegram/messenger/MessageObject;)Ljava/io/File;

    move-result-object p0

    return-object p0
.end method
