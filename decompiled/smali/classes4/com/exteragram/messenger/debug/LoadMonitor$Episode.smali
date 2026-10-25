.class final Lcom/exteragram/messenger/debug/LoadMonitor$Episode;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/debug/LoadMonitor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Episode"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\u0008\u0005\n\u0002\u0010$\n\u0002\u0010\u0008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\r\n\u0002\u0010\u000b\n\u0002\u0008\u0005\u0008\u0002\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0012\u0010\u0008\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0008\u0010\u000c\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0004\u0008\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0015\u0010\u0011R\u001d\u0010\u0008\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0016\u0010\u0017R\u0013\u0010\u000c\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u001c\u0010\u001d\"\u0004\u0008\u001e\u0010\u001f\u00a8\u0006 "
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/LoadMonitor$Episode;",
        "",
        "startedAt",
        "",
        "startRealtime",
        "cpuMs",
        "rxBytes",
        "txBytes",
        "threads",
        "",
        "",
        "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
        "health",
        "Landroid/os/health/HealthStats;",
        "<init>",
        "(JJJJJLjava/util/Map;Landroid/os/health/HealthStats;)V",
        "getStartedAt",
        "()J",
        "getStartRealtime",
        "getCpuMs",
        "getRxBytes",
        "getTxBytes",
        "getThreads",
        "()Ljava/util/Map;",
        "getHealth",
        "()Landroid/os/health/HealthStats;",
        "reported",
        "",
        "getReported",
        "()Z",
        "setReported",
        "(Z)V",
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
.field private final cpuMs:J

.field private final health:Landroid/os/health/HealthStats;

.field private reported:Z

.field private final rxBytes:J

.field private final startRealtime:J

.field private final startedAt:J

.field private final threads:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
            ">;"
        }
    .end annotation
.end field

.field private final txBytes:J


# direct methods
.method public constructor <init>(JJJJJLjava/util/Map;Landroid/os/health/HealthStats;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(JJJJJ",
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
            ">;",
            "Landroid/os/health/HealthStats;",
            ")V"
        }
    .end annotation

    .line 458
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 459
    iput-wide p1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->startedAt:J

    .line 460
    iput-wide p3, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->startRealtime:J

    .line 461
    iput-wide p5, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->cpuMs:J

    .line 462
    iput-wide p7, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->rxBytes:J

    .line 463
    iput-wide p9, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->txBytes:J

    .line 464
    iput-object p11, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->threads:Ljava/util/Map;

    .line 465
    iput-object p12, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->health:Landroid/os/health/HealthStats;

    return-void
.end method


# virtual methods
.method public final getCpuMs()J
    .locals 2

    .line 461
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->cpuMs:J

    return-wide v0
.end method

.method public final getHealth()Landroid/os/health/HealthStats;
    .locals 0

    .line 465
    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->health:Landroid/os/health/HealthStats;

    return-object p0
.end method

.method public final getReported()Z
    .locals 0

    .line 467
    iget-boolean p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->reported:Z

    return p0
.end method

.method public final getRxBytes()J
    .locals 2

    .line 462
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->rxBytes:J

    return-wide v0
.end method

.method public final getStartRealtime()J
    .locals 2

    .line 460
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->startRealtime:J

    return-wide v0
.end method

.method public final getStartedAt()J
    .locals 2

    .line 459
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->startedAt:J

    return-wide v0
.end method

.method public final getThreads()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Lcom/exteragram/messenger/debug/LoadMonitor$ThreadStat;",
            ">;"
        }
    .end annotation

    .line 464
    iget-object p0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->threads:Ljava/util/Map;

    return-object p0
.end method

.method public final getTxBytes()J
    .locals 2

    .line 463
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->txBytes:J

    return-wide v0
.end method

.method public final setReported(Z)V
    .locals 0

    .line 467
    iput-boolean p1, p0, Lcom/exteragram/messenger/debug/LoadMonitor$Episode;->reported:Z

    return-void
.end method
