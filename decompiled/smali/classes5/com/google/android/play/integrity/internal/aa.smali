.class final Lcom/google/android/play/integrity/internal/aa;
.super Lcom/google/android/play/integrity/internal/t;
.source "SourceFile"


# instance fields
.field final synthetic a:Landroid/os/IBinder;

.field final synthetic b:Lcom/google/android/play/integrity/internal/ad;


# direct methods
.method public constructor <init>(Lcom/google/android/play/integrity/internal/ad;Landroid/os/IBinder;)V
    .locals 0

    .line 1
    iput-object p2, p0, Lcom/google/android/play/integrity/internal/aa;->a:Landroid/os/IBinder;

    iput-object p1, p0, Lcom/google/android/play/integrity/internal/aa;->b:Lcom/google/android/play/integrity/internal/ad;

    invoke-direct {p0}, Lcom/google/android/play/integrity/internal/t;-><init>()V

    return-void
.end method


# virtual methods
.method public final b()V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/play/integrity/internal/aa;->b:Lcom/google/android/play/integrity/internal/ad;

    iget-object v0, v0, Lcom/google/android/play/integrity/internal/ad;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v0}, Lcom/google/android/play/integrity/internal/ae;->g(Lcom/google/android/play/integrity/internal/ae;)Lcom/google/android/play/integrity/internal/z;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/play/integrity/internal/aa;->a:Landroid/os/IBinder;

    invoke-interface {v0, v1}, Lcom/google/android/play/integrity/internal/z;->a(Landroid/os/IBinder;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/IInterface;

    iget-object v1, p0, Lcom/google/android/play/integrity/internal/aa;->b:Lcom/google/android/play/integrity/internal/ad;

    iget-object v1, v1, Lcom/google/android/play/integrity/internal/ad;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v1, v0}, Lcom/google/android/play/integrity/internal/ae;->n(Lcom/google/android/play/integrity/internal/ae;Landroid/os/IInterface;)V

    iget-object v0, p0, Lcom/google/android/play/integrity/internal/aa;->b:Lcom/google/android/play/integrity/internal/ad;

    iget-object v0, v0, Lcom/google/android/play/integrity/internal/ad;->a:Lcom/google/android/play/integrity/internal/ae;

    .line 2
    invoke-static {v0}, Lcom/google/android/play/integrity/internal/ae;->r(Lcom/google/android/play/integrity/internal/ae;)V

    iget-object v0, p0, Lcom/google/android/play/integrity/internal/aa;->b:Lcom/google/android/play/integrity/internal/ad;

    iget-object v0, v0, Lcom/google/android/play/integrity/internal/ad;->a:Lcom/google/android/play/integrity/internal/ae;

    const/4 v1, 0x0

    .line 3
    invoke-static {v0, v1}, Lcom/google/android/play/integrity/internal/ae;->m(Lcom/google/android/play/integrity/internal/ae;Z)V

    iget-object v0, p0, Lcom/google/android/play/integrity/internal/aa;->b:Lcom/google/android/play/integrity/internal/ad;

    iget-object v0, v0, Lcom/google/android/play/integrity/internal/ad;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v0}, Lcom/google/android/play/integrity/internal/ae;->i(Lcom/google/android/play/integrity/internal/ae;)Ljava/util/List;

    move-result-object v0

    .line 4
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Runnable;

    .line 5
    invoke-interface {v1}, Ljava/lang/Runnable;->run()V

    goto :goto_0

    :cond_0
    iget-object p0, p0, Lcom/google/android/play/integrity/internal/aa;->b:Lcom/google/android/play/integrity/internal/ad;

    iget-object p0, p0, Lcom/google/android/play/integrity/internal/ad;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {p0}, Lcom/google/android/play/integrity/internal/ae;->i(Lcom/google/android/play/integrity/internal/ae;)Ljava/util/List;

    move-result-object p0

    .line 6
    invoke-interface {p0}, Ljava/util/List;->clear()V

    return-void
.end method
