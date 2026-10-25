.class final Lcom/google/android/play/integrity/internal/x;
.super Lcom/google/android/play/integrity/internal/t;
.source "SourceFile"


# instance fields
.field final synthetic a:Lcom/google/android/play/integrity/internal/ae;


# direct methods
.method public constructor <init>(Lcom/google/android/play/integrity/internal/ae;)V
    .locals 0

    .line 1
    iput-object p1, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-direct {p0}, Lcom/google/android/play/integrity/internal/t;-><init>()V

    return-void
.end method


# virtual methods
.method public final b()V
    .locals 5

    .line 1
    iget-object v0, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v0}, Lcom/google/android/play/integrity/internal/ae;->h(Lcom/google/android/play/integrity/internal/ae;)Ljava/lang/Object;

    move-result-object v0

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v1}, Lcom/google/android/play/integrity/internal/ae;->j(Lcom/google/android/play/integrity/internal/ae;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicInteger;->get()I

    move-result v1

    const/4 v2, 0x0

    if-lez v1, :cond_0

    iget-object v1, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v1}, Lcom/google/android/play/integrity/internal/ae;->j(Lcom/google/android/play/integrity/internal/ae;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicInteger;->decrementAndGet()I

    move-result v1

    if-lez v1, :cond_0

    iget-object p0, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {p0}, Lcom/google/android/play/integrity/internal/ae;->f(Lcom/google/android/play/integrity/internal/ae;)Lcom/google/android/play/integrity/internal/s;

    move-result-object p0

    const-string v1, "Leaving the connection open for other ongoing calls."

    new-array v2, v2, [Ljava/lang/Object;

    .line 9
    invoke-virtual {p0, v1, v2}, Lcom/google/android/play/integrity/internal/s;->d(Ljava/lang/String;[Ljava/lang/Object;)I

    .line 10
    monitor-exit v0

    return-void

    :catchall_0
    move-exception p0

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v1}, Lcom/google/android/play/integrity/internal/ae;->d(Lcom/google/android/play/integrity/internal/ae;)Landroid/os/IInterface;

    move-result-object v3

    if-eqz v3, :cond_1

    invoke-static {v1}, Lcom/google/android/play/integrity/internal/ae;->f(Lcom/google/android/play/integrity/internal/ae;)Lcom/google/android/play/integrity/internal/s;

    move-result-object v1

    const-string v3, "Unbind from service."

    new-array v4, v2, [Ljava/lang/Object;

    .line 2
    invoke-virtual {v1, v3, v4}, Lcom/google/android/play/integrity/internal/s;->d(Ljava/lang/String;[Ljava/lang/Object;)I

    iget-object v1, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v1}, Lcom/google/android/play/integrity/internal/ae;->a(Lcom/google/android/play/integrity/internal/ae;)Landroid/content/Context;

    move-result-object v3

    invoke-static {v1}, Lcom/google/android/play/integrity/internal/ae;->b(Lcom/google/android/play/integrity/internal/ae;)Landroid/content/ServiceConnection;

    move-result-object v1

    .line 3
    invoke-virtual {v3, v1}, Landroid/content/Context;->unbindService(Landroid/content/ServiceConnection;)V

    iget-object v1, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    .line 4
    invoke-static {v1, v2}, Lcom/google/android/play/integrity/internal/ae;->m(Lcom/google/android/play/integrity/internal/ae;Z)V

    iget-object v1, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    const/4 v2, 0x0

    .line 5
    invoke-static {v1, v2}, Lcom/google/android/play/integrity/internal/ae;->n(Lcom/google/android/play/integrity/internal/ae;Landroid/os/IInterface;)V

    iget-object v1, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    .line 6
    invoke-static {v1, v2}, Lcom/google/android/play/integrity/internal/ae;->l(Lcom/google/android/play/integrity/internal/ae;Landroid/content/ServiceConnection;)V

    :cond_1
    iget-object p0, p0, Lcom/google/android/play/integrity/internal/x;->a:Lcom/google/android/play/integrity/internal/ae;

    .line 7
    invoke-static {p0}, Lcom/google/android/play/integrity/internal/ae;->p(Lcom/google/android/play/integrity/internal/ae;)V

    .line 8
    monitor-exit v0

    return-void

    :goto_0
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method
