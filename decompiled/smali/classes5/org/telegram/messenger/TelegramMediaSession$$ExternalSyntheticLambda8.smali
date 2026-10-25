.class public final synthetic Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda8;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/messenger/TelegramMediaSession;

.field public final synthetic f$1:Lorg/telegram/messenger/MessagesStorage;

.field public final synthetic f$2:I


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/messenger/TelegramMediaSession;Lorg/telegram/messenger/MessagesStorage;I)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda8;->f$0:Lorg/telegram/messenger/TelegramMediaSession;

    iput-object p2, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda8;->f$1:Lorg/telegram/messenger/MessagesStorage;

    iput p3, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda8;->f$2:I

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda8;->f$0:Lorg/telegram/messenger/TelegramMediaSession;

    iget-object v1, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda8;->f$1:Lorg/telegram/messenger/MessagesStorage;

    iget p0, p0, Lorg/telegram/messenger/TelegramMediaSession$$ExternalSyntheticLambda8;->f$2:I

    invoke-static {v0, v1, p0}, Lorg/telegram/messenger/TelegramMediaSession;->$r8$lambda$4uZUEr5G_uVIwgo8hdzGlYOR00w(Lorg/telegram/messenger/TelegramMediaSession;Lorg/telegram/messenger/MessagesStorage;I)V

    return-void
.end method
