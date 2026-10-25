.class abstract Lcom/google/android/play/core/integrity/ax;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static a:Lcom/google/android/play/core/integrity/aw;


# direct methods
.method public static declared-synchronized a(Landroid/content/Context;)Lcom/google/android/play/core/integrity/aw;
    .locals 3

    const-class v0, Lcom/google/android/play/core/integrity/ax;

    monitor-enter v0

    .line 1
    :try_start_0
    sget-object v1, Lcom/google/android/play/core/integrity/ax;->a:Lcom/google/android/play/core/integrity/aw;

    if-nez v1, :cond_0

    new-instance v1, Lcom/google/android/play/core/integrity/u;

    const/4 v2, 0x0

    invoke-direct {v1, v2}, Lcom/google/android/play/core/integrity/u;-><init>(Lcom/google/android/play/core/integrity/t;)V

    invoke-static {p0}, Lcom/google/android/play/integrity/internal/ag;->a(Landroid/content/Context;)Landroid/content/Context;

    move-result-object p0

    .line 2
    invoke-virtual {v1, p0}, Lcom/google/android/play/core/integrity/u;->a(Landroid/content/Context;)Lcom/google/android/play/core/integrity/u;

    .line 3
    invoke-interface {v1}, Lcom/google/android/play/core/integrity/av;->b()Lcom/google/android/play/core/integrity/aw;

    move-result-object p0

    sput-object p0, Lcom/google/android/play/core/integrity/ax;->a:Lcom/google/android/play/core/integrity/aw;

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    :cond_0
    :goto_0
    sget-object p0, Lcom/google/android/play/core/integrity/ax;->a:Lcom/google/android/play/core/integrity/aw;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object p0

    :goto_1
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method
