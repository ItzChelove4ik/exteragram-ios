.class public final Landroidx/lifecycle/FastSafeIterableMap;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/lifecycle/FastSafeIterableMap$Entry;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<K:",
        "Ljava/lang/Object;",
        "V:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0002\u0008\u0008\n\u0002\u0010&\n\u0002\u0008\u0005\n\u0002\u0010\u0008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\u0008\u0000\u0018\u0000*\u0008\u0008\u0000\u0010\u0001*\u00020\u0002*\u0008\u0008\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0001!B\u0007\u00a2\u0006\u0004\u0008\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\u000c2\u0006\u0010\r\u001a\u00028\u0000H\u0086\u0002\u00a2\u0006\u0002\u0010\u000eJ\u001d\u0010\u000f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u0001\u00a2\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u0004\u0018\u00018\u00012\u0006\u0010\r\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0013J!\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00152\u0006\u0010\r\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0016J\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015J\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015J\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0015J\u0006\u0010\u001a\u001a\u00020\u001bJ&\u0010\u001c\u001a\u00020\u001d2\u001e\u0010\u001e\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015\u0012\u0004\u0012\u00020\u001d0\u001fJ&\u0010 \u001a\u00020\u001d2\u001e\u0010\u001e\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015\u0012\u0004\u0012\u00020\u001d0\u001fR&\u0010\u0006\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00080\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""
    }
    d2 = {
        "Landroidx/lifecycle/FastSafeIterableMap;",
        "K",
        "",
        "V",
        "<init>",
        "()V",
        "map",
        "Landroidx/collection/MutableScatterMap;",
        "Landroidx/lifecycle/FastSafeIterableMap$Entry;",
        "head",
        "tail",
        "contains",
        "",
        "key",
        "(Ljava/lang/Object;)Z",
        "putIfAbsent",
        "value",
        "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
        "remove",
        "(Ljava/lang/Object;)Ljava/lang/Object;",
        "ceil",
        "",
        "(Ljava/lang/Object;)Ljava/util/Map$Entry;",
        "first",
        "last",
        "lastOrNull",
        "size",
        "",
        "forEachWithAdditions",
        "",
        "action",
        "Lkotlin/Function1;",
        "forEachReversed",
        "Entry",
        "lifecycle-runtime"
    }
    k = 0x1
    mv = {
        0x2,
        0x1,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field private head:Landroidx/lifecycle/FastSafeIterableMap$Entry;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/lifecycle/FastSafeIterableMap$Entry<",
            "TK;TV;>;"
        }
    .end annotation
.end field

.field private final map:Landroidx/collection/MutableScatterMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/collection/MutableScatterMap<",
            "TK;",
            "Landroidx/lifecycle/FastSafeIterableMap$Entry<",
            "TK;TV;>;>;"
        }
    .end annotation
.end field

.field private tail:Landroidx/lifecycle/FastSafeIterableMap$Entry;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroidx/lifecycle/FastSafeIterableMap$Entry<",
            "TK;TV;>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 4

    .line 39
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 42
    new-instance v0, Landroidx/collection/MutableScatterMap;

    const/4 v1, 0x1

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-direct {v0, v3, v1, v2}, Landroidx/collection/MutableScatterMap;-><init>(IILkotlin/jvm/internal/DefaultConstructorMarker;)V

    iput-object v0, p0, Landroidx/lifecycle/FastSafeIterableMap;->map:Landroidx/collection/MutableScatterMap;

    return-void
.end method


# virtual methods
.method public final ceil(Ljava/lang/Object;)Ljava/util/Map$Entry;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;)",
            "Ljava/util/Map$Entry<",
            "TK;TV;>;"
        }
    .end annotation

    .line 107
    iget-object p0, p0, Landroidx/lifecycle/FastSafeIterableMap;->map:Landroidx/collection/MutableScatterMap;

    invoke-virtual {p0, p1}, Landroidx/collection/ScatterMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Landroidx/lifecycle/FastSafeIterableMap$Entry;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getPrev()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public final contains(Ljava/lang/Object;)Z
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;)Z"
        }
    .end annotation

    .line 53
    iget-object p0, p0, Landroidx/lifecycle/FastSafeIterableMap;->map:Landroidx/collection/MutableScatterMap;

    invoke-virtual {p0, p1}, Landroidx/collection/ScatterMap;->containsKey(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public final first()Ljava/util/Map$Entry;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map$Entry<",
            "TK;TV;>;"
        }
    .end annotation

    .line 114
    iget-object p0, p0, Landroidx/lifecycle/FastSafeIterableMap;->head:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "Collection is empty."

    invoke-static {p0}, Lkotlin/UIntArray$Iterator$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final forEachReversed(Lkotlin/jvm/functions/Function1;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/util/Map$Entry<",
            "+TK;+TV;>;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 154
    iget-object p0, p0, Landroidx/lifecycle/FastSafeIterableMap;->tail:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    :goto_0
    if-eqz p0, :cond_1

    .line 158
    invoke-virtual {p0}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->isRemoved()Z

    move-result v0

    if-nez v0, :cond_0

    .line 159
    invoke-interface {p1, p0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    .line 164
    :cond_0
    invoke-virtual {p0}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getPrev()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object p0

    goto :goto_0

    :cond_1
    return-void
.end method

.method public final forEachWithAdditions(Lkotlin/jvm/functions/Function1;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Ljava/util/Map$Entry<",
            "+TK;+TV;>;",
            "Lkotlin/Unit;",
            ">;)V"
        }
    .end annotation

    .line 136
    iget-object p0, p0, Landroidx/lifecycle/FastSafeIterableMap;->head:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    :goto_0
    if-eqz p0, :cond_1

    .line 140
    invoke-virtual {p0}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->isRemoved()Z

    move-result v0

    if-nez v0, :cond_0

    .line 141
    invoke-interface {p1, p0}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    .line 145
    :cond_0
    invoke-virtual {p0}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getNext()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object p0

    goto :goto_0

    :cond_1
    return-void
.end method

.method public final last()Ljava/util/Map$Entry;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map$Entry<",
            "TK;TV;>;"
        }
    .end annotation

    .line 121
    iget-object p0, p0, Landroidx/lifecycle/FastSafeIterableMap;->tail:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    if-eqz p0, :cond_0

    return-object p0

    :cond_0
    const-string p0, "Collection is empty."

    invoke-static {p0}, Lkotlin/UIntArray$Iterator$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public final lastOrNull()Ljava/util/Map$Entry;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map$Entry<",
            "TK;TV;>;"
        }
    .end annotation

    .line 123
    iget-object p0, p0, Landroidx/lifecycle/FastSafeIterableMap;->tail:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    return-object p0
.end method

.method public final putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;TV;)TV;"
        }
    .end annotation

    .line 60
    iget-object v0, p0, Landroidx/lifecycle/FastSafeIterableMap;->map:Landroidx/collection/MutableScatterMap;

    invoke-virtual {v0, p1}, Landroidx/collection/ScatterMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroidx/lifecycle/FastSafeIterableMap$Entry;

    if-eqz v0, :cond_0

    .line 62
    invoke-virtual {v0}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getValue()Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 65
    :cond_0
    new-instance v0, Landroidx/lifecycle/FastSafeIterableMap$Entry;

    invoke-direct {v0, p1, p2}, Landroidx/lifecycle/FastSafeIterableMap$Entry;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 66
    iget-object p2, p0, Landroidx/lifecycle/FastSafeIterableMap;->map:Landroidx/collection/MutableScatterMap;

    invoke-virtual {p2, p1, v0}, Landroidx/collection/MutableScatterMap;->set(Ljava/lang/Object;Ljava/lang/Object;)V

    .line 68
    iget-object p1, p0, Landroidx/lifecycle/FastSafeIterableMap;->tail:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    if-nez p1, :cond_1

    .line 69
    iput-object v0, p0, Landroidx/lifecycle/FastSafeIterableMap;->head:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    .line 70
    iput-object v0, p0, Landroidx/lifecycle/FastSafeIterableMap;->tail:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    goto :goto_0

    :cond_1
    if-eqz p1, :cond_2

    .line 72
    invoke-virtual {p1, v0}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->setNext(Landroidx/lifecycle/FastSafeIterableMap$Entry;)V

    .line 73
    :cond_2
    iget-object p1, p0, Landroidx/lifecycle/FastSafeIterableMap;->tail:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    invoke-virtual {v0, p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->setPrev(Landroidx/lifecycle/FastSafeIterableMap$Entry;)V

    .line 74
    iput-object v0, p0, Landroidx/lifecycle/FastSafeIterableMap;->tail:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    :goto_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public final remove(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;)TV;"
        }
    .end annotation

    .line 87
    iget-object v0, p0, Landroidx/lifecycle/FastSafeIterableMap;->map:Landroidx/collection/MutableScatterMap;

    invoke-virtual {v0, p1}, Landroidx/collection/MutableScatterMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroidx/lifecycle/FastSafeIterableMap$Entry;

    if-nez p1, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 90
    :cond_0
    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getPrev()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object v0

    if-nez v0, :cond_1

    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getNext()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object v0

    iput-object v0, p0, Landroidx/lifecycle/FastSafeIterableMap;->head:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    goto :goto_0

    :cond_1
    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getPrev()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object v0

    if-eqz v0, :cond_2

    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getNext()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->setNext(Landroidx/lifecycle/FastSafeIterableMap$Entry;)V

    .line 91
    :cond_2
    :goto_0
    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getNext()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object v0

    if-nez v0, :cond_3

    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getPrev()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object v0

    iput-object v0, p0, Landroidx/lifecycle/FastSafeIterableMap;->tail:Landroidx/lifecycle/FastSafeIterableMap$Entry;

    goto :goto_1

    :cond_3
    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getNext()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object p0

    if-eqz p0, :cond_4

    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getPrev()Landroidx/lifecycle/FastSafeIterableMap$Entry;

    move-result-object v0

    invoke-virtual {p0, v0}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->setPrev(Landroidx/lifecycle/FastSafeIterableMap$Entry;)V

    .line 95
    :cond_4
    :goto_1
    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->markRemoved()V

    .line 97
    invoke-virtual {p1}, Landroidx/lifecycle/FastSafeIterableMap$Entry;->getValue()Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final size()I
    .locals 0

    .line 126
    iget-object p0, p0, Landroidx/lifecycle/FastSafeIterableMap;->map:Landroidx/collection/MutableScatterMap;

    invoke-virtual {p0}, Landroidx/collection/ScatterMap;->getSize()I

    move-result p0

    return p0
.end method
