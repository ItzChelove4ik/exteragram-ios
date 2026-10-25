.class public Lorg/telegram/messenger/SharedConfig$ProxyInfo;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/messenger/SharedConfig;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "ProxyInfo"
.end annotation


# instance fields
.field public available:Z

.field public availableCheckTime:J

.field public checking:Z

.field public ping:J

.field public settings:Lorg/telegram/utils/proxy/ProxySettings;


# direct methods
.method public constructor <init>(Lorg/telegram/utils/proxy/ProxySettings;)V
    .locals 0

    .line 380
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 381
    iput-object p1, p0, Lorg/telegram/messenger/SharedConfig$ProxyInfo;->settings:Lorg/telegram/utils/proxy/ProxySettings;

    return-void
.end method

.method public static normalizeAvailableCheckTime(J)J
    .locals 4

    const-wide/16 v0, 0x0

    cmp-long v2, p0, v0

    if-lez v2, :cond_1

    .line 385
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v2

    cmp-long v2, p0, v2

    if-lez v2, :cond_0

    goto :goto_0

    :cond_0
    return-wide p0

    :cond_1
    :goto_0
    return-wide v0
.end method
