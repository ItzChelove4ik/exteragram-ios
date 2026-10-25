.class final Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/debug/LoadMonitor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "ThreadStat"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0007\u0008\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\u0008\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0008\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\n\u0010\u000b\u00a8\u0006\u000c"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
        "",
        "name",
        "",
        "ticks",
        "",
        "<init>",
        "(Ljava/lang/String;J)V",
        "getName",
        "()Ljava/lang/String;",
        "getTicks",
        "()J",
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
.field private final name:Ljava/lang/String;

.field private final ticks:J


# direct methods
.method public constructor <init>(Ljava/lang/String;J)V
    .locals 0

    .line 454
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->name:Ljava/lang/String;

    iput-wide p2, p0, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->ticks:J

    return-void
.end method


# virtual methods
.method public final getName()Ljava/lang/String;
    .locals 0

    .line 454
    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->name:Ljava/lang/String;

    return-object p0
.end method

.method public final getTicks()J
    .locals 2

    .line 454
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;->ticks:J

    return-wide v0
.end method
