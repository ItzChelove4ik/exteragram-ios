.class final Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/exteragram/messenger/debug/FreezeMonitor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Freeze"
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0010\u0016\n\u0002\u0008\u0002\n\u0002\u0010$\n\u0002\u0008\u0004\n\u0002\u0010 \n\u0002\u0008\u0019\u0008\u0002\u0018\u00002\u00020\u0001B\u00c5\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\u0008\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u000c\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u0018\u0010\u0013\u001a\u0014\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\t0\u0014\u0012\u0004\u0012\u00020\u00050\u000f\u0012\u0008\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\u0008\u0010\u0016\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\u0008\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u001d\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u001e\u0010\u001aR\u0011\u0010\u0008\u001a\u00020\t\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u001f\u0010 R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008!\u0010 R\u0011\u0010\u000b\u001a\u00020\u000c\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\"\u0010#R\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008$\u0010 R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008%\u0010&R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\'\u0010&R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008(\u0010&R\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\u000f\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008)\u0010&R#\u0010\u0013\u001a\u0014\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\t0\u0014\u0012\u0004\u0012\u00020\u00050\u000f\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008*\u0010&R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008+\u0010 R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008,\u0010 \u00a8\u0006-"
    }
    d2 = {
        "Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;",
        "",
        "durationMs",
        "",
        "thresholdMs",
        "",
        "samples",
        "maxGapMs",
        "screen",
        "",
        "fragmentStack",
        "gcBefore",
        "",
        "heapBefore",
        "states",
        "",
        "hot",
        "hotDepth",
        "topFrames",
        "stacks",
        "",
        "otherThreads",
        "observers",
        "<init>",
        "(JIIJLjava/lang/String;Ljava/lang/String;[JLjava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V",
        "getDurationMs",
        "()J",
        "getThresholdMs",
        "()I",
        "getSamples",
        "getMaxGapMs",
        "getScreen",
        "()Ljava/lang/String;",
        "getFragmentStack",
        "getGcBefore",
        "()[J",
        "getHeapBefore",
        "getStates",
        "()Ljava/util/Map;",
        "getHot",
        "getHotDepth",
        "getTopFrames",
        "getStacks",
        "getOtherThreads",
        "getObservers",
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
.field private final durationMs:J

.field private final fragmentStack:Ljava/lang/String;

.field private final gcBefore:[J

.field private final heapBefore:Ljava/lang/String;

.field private final hot:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final hotDepth:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final maxGapMs:J

.field private final observers:Ljava/lang/String;

.field private final otherThreads:Ljava/lang/String;

.field private final samples:I

.field private final screen:Ljava/lang/String;

.field private final stacks:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final states:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final thresholdMs:I

.field private final topFrames:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(JIIJLjava/lang/String;Ljava/lang/String;[JLjava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(JIIJ",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "[J",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/Integer;",
            ">;",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    .line 332
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 333
    iput-wide p1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->durationMs:J

    .line 334
    iput p3, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->thresholdMs:I

    .line 335
    iput p4, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->samples:I

    .line 336
    iput-wide p5, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->maxGapMs:J

    .line 337
    iput-object p7, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->screen:Ljava/lang/String;

    .line 338
    iput-object p8, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->fragmentStack:Ljava/lang/String;

    .line 339
    iput-object p9, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->gcBefore:[J

    .line 340
    iput-object p10, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->heapBefore:Ljava/lang/String;

    .line 341
    iput-object p11, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->states:Ljava/util/Map;

    .line 342
    iput-object p12, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->hot:Ljava/util/Map;

    .line 343
    iput-object p13, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->hotDepth:Ljava/util/Map;

    .line 344
    iput-object p14, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->topFrames:Ljava/util/Map;

    .line 345
    iput-object p15, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->stacks:Ljava/util/Map;

    move-object/from16 p1, p16

    .line 346
    iput-object p1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->otherThreads:Ljava/lang/String;

    move-object/from16 p1, p17

    .line 347
    iput-object p1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->observers:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final getDurationMs()J
    .locals 2

    .line 333
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->durationMs:J

    return-wide v0
.end method

.method public final getFragmentStack()Ljava/lang/String;
    .locals 0

    .line 338
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->fragmentStack:Ljava/lang/String;

    return-object p0
.end method

.method public final getGcBefore()[J
    .locals 0

    .line 339
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->gcBefore:[J

    return-object p0
.end method

.method public final getHeapBefore()Ljava/lang/String;
    .locals 0

    .line 340
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->heapBefore:Ljava/lang/String;

    return-object p0
.end method

.method public final getHot()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 342
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->hot:Ljava/util/Map;

    return-object p0
.end method

.method public final getHotDepth()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 343
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->hotDepth:Ljava/util/Map;

    return-object p0
.end method

.method public final getMaxGapMs()J
    .locals 2

    .line 336
    iget-wide v0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->maxGapMs:J

    return-wide v0
.end method

.method public final getObservers()Ljava/lang/String;
    .locals 0

    .line 347
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->observers:Ljava/lang/String;

    return-object p0
.end method

.method public final getOtherThreads()Ljava/lang/String;
    .locals 0

    .line 346
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->otherThreads:Ljava/lang/String;

    return-object p0
.end method

.method public final getSamples()I
    .locals 0

    .line 335
    iget p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->samples:I

    return p0
.end method

.method public final getScreen()Ljava/lang/String;
    .locals 0

    .line 337
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->screen:Ljava/lang/String;

    return-object p0
.end method

.method public final getStacks()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 345
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->stacks:Ljava/util/Map;

    return-object p0
.end method

.method public final getStates()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 341
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->states:Ljava/util/Map;

    return-object p0
.end method

.method public final getThresholdMs()I
    .locals 0

    .line 334
    iget p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->thresholdMs:I

    return p0
.end method

.method public final getTopFrames()Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 344
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->topFrames:Ljava/util/Map;

    return-object p0
.end method
