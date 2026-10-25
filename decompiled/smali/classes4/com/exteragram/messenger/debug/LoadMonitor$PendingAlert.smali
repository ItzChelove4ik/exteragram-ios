.class final Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/debug/LoadMonitor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "PendingAlert"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0008\u0007\u0008\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\u0008\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0008\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\n\u0010\u000b\u00a8\u0006\u000c"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;",
        "",
        "file",
        "Ljava/io/File;",
        "message",
        "",
        "<init>",
        "(Ljava/io/File;Ljava/lang/String;)V",
        "getFile",
        "()Ljava/io/File;",
        "getMessage",
        "()Ljava/lang/String;",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field private final file:Ljava/io/File;

.field private final message:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/io/File;Ljava/lang/String;)V
    .locals 0

    .line 456
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;->file:Ljava/io/File;

    iput-object p2, p0, Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;->message:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final getFile()Ljava/io/File;
    .locals 0

    .line 456
    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;->file:Ljava/io/File;

    return-object p0
.end method

.method public final getMessage()Ljava/lang/String;
    .locals 0

    .line 456
    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$PendingAlert;->message:Ljava/lang/String;

    return-object p0
.end method
