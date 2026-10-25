.class public final Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/util/Comparator;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog;->record(Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Ljava/util/Comparator;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    k = 0x3
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$thenByDescending$1\n+ 2 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor$Watchdog\n*L\n1#1,328:1\n486#2:329\n*E\n"
    }
.end annotation


# instance fields
.field final synthetic $freeze$inlined:Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;

.field final synthetic $this_thenByDescending:Ljava/util/Comparator;


# direct methods
.method public constructor <init>(Ljava/util/Comparator;Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1;->$this_thenByDescending:Ljava/util/Comparator;

    iput-object p2, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1;->$freeze$inlined:Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final compare(Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;TT;)I"
        }
    .end annotation

    const/4 v0, 0x0

    .line 329
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    .line 170
    iget-object v1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1;->$this_thenByDescending:Ljava/util/Comparator;

    invoke-interface {v1, p1, p2}, Ljava/util/Comparator;->compare(Ljava/lang/Object;Ljava/lang/Object;)I

    move-result v1

    if-eqz v1, :cond_0

    return v1

    .line 171
    :cond_0
    check-cast p2, Ljava/util/Map$Entry;

    .line 329
    iget-object v1, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1;->$freeze$inlined:Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;

    invoke-virtual {v1}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getHotDepth()Ljava/util/Map;

    move-result-object v1

    invoke-interface {p2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object p2

    invoke-interface {v1, p2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/Integer;

    if-eqz p2, :cond_1

    goto :goto_0

    :cond_1
    move-object p2, v0

    .line 171
    :goto_0
    check-cast p1, Ljava/util/Map$Entry;

    .line 329
    iget-object p0, p0, Lcom/exteragram/messenger/debug/FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1;->$freeze$inlined:Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;

    invoke-virtual {p0}, Lcom/exteragram/messenger/debug/FreezeMonitor$Freeze;->getHotDepth()Ljava/util/Map;

    move-result-object p0

    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object p1

    invoke-interface {p0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    if-eqz p0, :cond_2

    move-object v0, p0

    .line 171
    :cond_2
    invoke-static {p2, v0}, Lkotlin/comparisons/ComparisonsKt;->compareValues(Ljava/lang/Comparable;Ljava/lang/Comparable;)I

    move-result p0

    return p0
.end method
