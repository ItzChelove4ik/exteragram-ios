.class final Lcom/google/android/play/core/integrity/w;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/play/core/integrity/aw;


# instance fields
.field private final a:Lcom/google/android/play/integrity/internal/bb;

.field private final b:Lcom/google/android/play/integrity/internal/bb;

.field private final c:Lcom/google/android/play/integrity/internal/bb;

.field private final d:Lcom/google/android/play/integrity/internal/bb;

.field private final e:Lcom/google/android/play/integrity/internal/bb;

.field private final f:Lcom/google/android/play/integrity/internal/bb;


# direct methods
.method public synthetic constructor <init>(Landroid/content/Context;Lcom/google/android/play/core/integrity/v;)V
    .locals 3

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-static {p1}, Lcom/google/android/play/integrity/internal/az;->b(Ljava/lang/Object;)Lcom/google/android/play/integrity/internal/ay;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/play/core/integrity/w;->a:Lcom/google/android/play/integrity/internal/bb;

    invoke-static {}, Lcom/google/android/play/core/integrity/bb;->a()Lcom/google/android/play/core/integrity/bc;

    move-result-object p2

    invoke-static {p2}, Lcom/google/android/play/integrity/internal/ax;->b(Lcom/google/android/play/integrity/internal/bb;)Lcom/google/android/play/integrity/internal/bb;

    move-result-object p2

    iput-object p2, p0, Lcom/google/android/play/core/integrity/w;->b:Lcom/google/android/play/integrity/internal/bb;

    invoke-static {}, Lcom/google/android/play/core/integrity/n;->a()Lcom/google/android/play/core/integrity/o;

    move-result-object v0

    new-instance v1, Lcom/google/android/play/core/integrity/au;

    invoke-direct {v1, p1, v0}, Lcom/google/android/play/core/integrity/au;-><init>(Lcom/google/android/play/integrity/internal/bd;Lcom/google/android/play/integrity/internal/bd;)V

    iput-object v1, p0, Lcom/google/android/play/core/integrity/w;->c:Lcom/google/android/play/integrity/internal/bb;

    invoke-static {}, Lcom/google/android/play/core/integrity/n;->a()Lcom/google/android/play/core/integrity/o;

    move-result-object v0

    new-instance v2, Lcom/google/android/play/core/integrity/bp;

    invoke-direct {v2, p1, p2, v1, v0}, Lcom/google/android/play/core/integrity/bp;-><init>(Lcom/google/android/play/integrity/internal/bd;Lcom/google/android/play/integrity/internal/bd;Lcom/google/android/play/integrity/internal/bd;Lcom/google/android/play/integrity/internal/bd;)V

    invoke-static {v2}, Lcom/google/android/play/integrity/internal/ax;->b(Lcom/google/android/play/integrity/internal/bb;)Lcom/google/android/play/integrity/internal/bb;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/play/core/integrity/w;->d:Lcom/google/android/play/integrity/internal/bb;

    new-instance p2, Lcom/google/android/play/core/integrity/bu;

    invoke-direct {p2, p1}, Lcom/google/android/play/core/integrity/bu;-><init>(Lcom/google/android/play/integrity/internal/bd;)V

    invoke-static {p2}, Lcom/google/android/play/integrity/internal/ax;->b(Lcom/google/android/play/integrity/internal/bb;)Lcom/google/android/play/integrity/internal/bb;

    move-result-object p2

    iput-object p2, p0, Lcom/google/android/play/core/integrity/w;->e:Lcom/google/android/play/integrity/internal/bb;

    new-instance v0, Lcom/google/android/play/core/integrity/ba;

    invoke-direct {v0, p1, p2}, Lcom/google/android/play/core/integrity/ba;-><init>(Lcom/google/android/play/integrity/internal/bd;Lcom/google/android/play/integrity/internal/bd;)V

    invoke-static {v0}, Lcom/google/android/play/integrity/internal/ax;->b(Lcom/google/android/play/integrity/internal/bb;)Lcom/google/android/play/integrity/internal/bb;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/play/core/integrity/w;->f:Lcom/google/android/play/integrity/internal/bb;

    return-void
.end method


# virtual methods
.method public final a()Lcom/google/android/play/core/integrity/StandardIntegrityManager;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/play/core/integrity/w;->f:Lcom/google/android/play/integrity/internal/bb;

    invoke-interface {p0}, Lcom/google/android/play/integrity/internal/bd;->a()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/play/core/integrity/StandardIntegrityManager;

    return-object p0
.end method
