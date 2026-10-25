.class public final synthetic Lorg/telegram/messenger/TelegramMediaSession$SessionCallback$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field public final synthetic f$0:Lorg/telegram/messenger/TelegramMediaSession$SessionCallback;

.field public final synthetic f$1:Ljava/lang/String;

.field public final synthetic f$2:Landroid/os/Bundle;


# direct methods
.method public synthetic constructor <init>(Lorg/telegram/messenger/TelegramMediaSession$SessionCallback;Ljava/lang/String;Landroid/os/Bundle;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lorg/telegram/messenger/TelegramMediaSession$SessionCallback$$ExternalSyntheticLambda0;->f$0:Lorg/telegram/messenger/TelegramMediaSession$SessionCallback;

    iput-object p2, p0, Lorg/telegram/messenger/TelegramMediaSession$SessionCallback$$ExternalSyntheticLambda0;->f$1:Ljava/lang/String;

    iput-object p3, p0, Lorg/telegram/messenger/TelegramMediaSession$SessionCallback$$ExternalSyntheticLambda0;->f$2:Landroid/os/Bundle;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    .line 0
    iget-object v0, p0, Lorg/telegram/messenger/TelegramMediaSession$SessionCallback$$ExternalSyntheticLambda0;->f$0:Lorg/telegram/messenger/TelegramMediaSession$SessionCallback;

    iget-object v1, p0, Lorg/telegram/messenger/TelegramMediaSession$SessionCallback$$ExternalSyntheticLambda0;->f$1:Ljava/lang/String;

    iget-object p0, p0, Lorg/telegram/messenger/TelegramMediaSession$SessionCallback$$ExternalSyntheticLambda0;->f$2:Landroid/os/Bundle;

    invoke-static {v0, v1, p0}, Lorg/telegram/messenger/TelegramMediaSession$SessionCallback;->$r8$lambda$6bdmqF1A8ktUCj_9tn6ZA9JV-2U(Lorg/telegram/messenger/TelegramMediaSession$SessionCallback;Ljava/lang/String;Landroid/os/Bundle;)V

    return-void
.end method
