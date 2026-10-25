.class Lorg/telegram/messenger/MessagesStorage$Hole;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/MessagesStorage;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Hole"
.end annotation


# instance fields
.field public end:I

.field public start:I

.field public type:I


# direct methods
.method public constructor <init>(II)V
    .locals 0

    .line 15698
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 15699
    iput p1, p0, Lorg/telegram/messenger/MessagesStorage$Hole;->start:I

    .line 15700
    iput p2, p0, Lorg/telegram/messenger/MessagesStorage$Hole;->end:I

    return-void
.end method

.method public constructor <init>(III)V
    .locals 0

    .line 15703
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 15704
    iput p1, p0, Lorg/telegram/messenger/MessagesStorage$Hole;->type:I

    .line 15705
    iput p2, p0, Lorg/telegram/messenger/MessagesStorage$Hole;->start:I

    .line 15706
    iput p3, p0, Lorg/telegram/messenger/MessagesStorage$Hole;->end:I

    return-void
.end method
