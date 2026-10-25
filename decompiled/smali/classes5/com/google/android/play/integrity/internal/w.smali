.class final Lcom/google/android/play/integrity/internal/w;
.super Lcom/google/android/play/integrity/internal/t;
.source "SourceFile"


# instance fields
.field final synthetic a:Lcom/google/android/gms/tasks/TaskCompletionSource;

.field final synthetic b:Lcom/google/android/play/integrity/internal/t;

.field final synthetic c:Lcom/google/android/play/integrity/internal/ae;


# direct methods
.method public constructor <init>(Lcom/google/android/play/integrity/internal/ae;Lcom/google/android/gms/tasks/TaskCompletionSource;Lcom/google/android/gms/tasks/TaskCompletionSource;Lcom/google/android/play/integrity/internal/t;)V
    .locals 0

    .line 1
    iput-object p3, p0, Lcom/google/android/play/integrity/internal/w;->a:Lcom/google/android/gms/tasks/TaskCompletionSource;

    iput-object p4, p0, Lcom/google/android/play/integrity/internal/w;->b:Lcom/google/android/play/integrity/internal/t;

    iput-object p1, p0, Lcom/google/android/play/integrity/internal/w;->c:Lcom/google/android/play/integrity/internal/ae;

    invoke-direct {p0, p2}, Lcom/google/android/play/integrity/internal/t;-><init>(Lcom/google/android/gms/tasks/TaskCompletionSource;)V

    return-void
.end method


# virtual methods
.method public final b()V
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/play/integrity/internal/w;->c:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v0}, Lcom/google/android/play/integrity/internal/ae;->h(Lcom/google/android/play/integrity/internal/ae;)Ljava/lang/Object;

    move-result-object v0

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/google/android/play/integrity/internal/w;->c:Lcom/google/android/play/integrity/internal/ae;

    iget-object v2, p0, Lcom/google/android/play/integrity/internal/w;->a:Lcom/google/android/gms/tasks/TaskCompletionSource;

    invoke-static {v1, v2}, Lcom/google/android/play/integrity/internal/ae;->o(Lcom/google/android/play/integrity/internal/ae;Lcom/google/android/gms/tasks/TaskCompletionSource;)V

    iget-object v1, p0, Lcom/google/android/play/integrity/internal/w;->c:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v1}, Lcom/google/android/play/integrity/internal/ae;->j(Lcom/google/android/play/integrity/internal/ae;)Ljava/util/concurrent/atomic/AtomicInteger;

    move-result-object v1

    .line 2
    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicInteger;->getAndIncrement()I

    move-result v1

    if-lez v1, :cond_0

    iget-object v1, p0, Lcom/google/android/play/integrity/internal/w;->c:Lcom/google/android/play/integrity/internal/ae;

    invoke-static {v1}, Lcom/google/android/play/integrity/internal/ae;->f(Lcom/google/android/play/integrity/internal/ae;)Lcom/google/android/play/integrity/internal/s;

    move-result-object v1

    const-string v2, "Already connected to the service."

    const/4 v3, 0x0

    new-array v3, v3, [Ljava/lang/Object;

    .line 3
    invoke-virtual {v1, v2, v3}, Lcom/google/android/play/integrity/internal/s;->d(Ljava/lang/String;[Ljava/lang/Object;)I

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    :goto_0
    iget-object v1, p0, Lcom/google/android/play/integrity/internal/w;->c:Lcom/google/android/play/integrity/internal/ae;

    iget-object p0, p0, Lcom/google/android/play/integrity/internal/w;->b:Lcom/google/android/play/integrity/internal/t;

    .line 4
    invoke-static {v1, p0}, Lcom/google/android/play/integrity/internal/ae;->q(Lcom/google/android/play/integrity/internal/ae;Lcom/google/android/play/integrity/internal/t;)V

    .line 5
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method
