.class final Lorg/telegram/messenger/TelegramMediaSession$PendingBrowseRequest;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/TelegramMediaSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "PendingBrowseRequest"
.end annotation


# instance fields
.field final callback:Lorg/telegram/messenger/TelegramMediaSession$BrowseChildrenCallback;

.field final parentMediaId:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lorg/telegram/messenger/TelegramMediaSession$BrowseChildrenCallback;)V
    .locals 0

    .line 267
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 268
    iput-object p1, p0, Lorg/telegram/messenger/TelegramMediaSession$PendingBrowseRequest;->parentMediaId:Ljava/lang/String;

    .line 269
    iput-object p2, p0, Lorg/telegram/messenger/TelegramMediaSession$PendingBrowseRequest;->callback:Lorg/telegram/messenger/TelegramMediaSession$BrowseChildrenCallback;

    return-void
.end method
