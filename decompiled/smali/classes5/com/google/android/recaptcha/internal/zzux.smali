.class public final Lcom/google/android/recaptcha/internal/zzux;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzux;


# instance fields
.field private final zzb:Ljava/util/concurrent/atomic/AtomicReference;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzux;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzux;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzux;->zza:Lcom/google/android/recaptcha/internal/zzux;

    return-void
.end method

.method public constructor <init>()V
    .locals 4

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/concurrent/atomic/AtomicReference;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzvq;

    const/4 v2, 0x0

    invoke-direct {v1, v2}, Lcom/google/android/recaptcha/internal/zzvq;-><init>([B)V

    new-instance v3, Lcom/google/android/recaptcha/internal/zzvs;

    .line 2
    invoke-direct {v3, v1, v2}, Lcom/google/android/recaptcha/internal/zzvs;-><init>(Lcom/google/android/recaptcha/internal/zzvq;[B)V

    .line 3
    invoke-direct {v0, v3}, Ljava/util/concurrent/atomic/AtomicReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzux;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzux;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzux;->zza:Lcom/google/android/recaptcha/internal/zzux;

    return-object v0
.end method


# virtual methods
.method public final declared-synchronized zzb(Lcom/google/android/recaptcha/internal/zzvp;)V
    .locals 4

    monitor-enter p0

    .line 1
    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzux;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzvs;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzvq;

    const/4 v3, 0x0

    .line 2
    invoke-direct {v2, v1, v3}, Lcom/google/android/recaptcha/internal/zzvq;-><init>(Lcom/google/android/recaptcha/internal/zzvs;[B)V

    .line 3
    invoke-virtual {v2, p1}, Lcom/google/android/recaptcha/internal/zzvq;->zza(Lcom/google/android/recaptcha/internal/zzvp;)Lcom/google/android/recaptcha/internal/zzvq;

    new-instance p1, Lcom/google/android/recaptcha/internal/zzvs;

    .line 4
    invoke-direct {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzvs;-><init>(Lcom/google/android/recaptcha/internal/zzvq;[B)V

    .line 5
    invoke-virtual {v0, p1}, Ljava/util/concurrent/atomic/AtomicReference;->set(Ljava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method public final declared-synchronized zzc(Lcom/google/android/recaptcha/internal/zzvu;)V
    .locals 4

    monitor-enter p0

    .line 1
    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzux;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzvs;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzvq;

    const/4 v3, 0x0

    .line 2
    invoke-direct {v2, v1, v3}, Lcom/google/android/recaptcha/internal/zzvq;-><init>(Lcom/google/android/recaptcha/internal/zzvs;[B)V

    .line 1
    invoke-virtual {v2, p1}, Lcom/google/android/recaptcha/internal/zzvq;->zzb(Lcom/google/android/recaptcha/internal/zzvu;)Lcom/google/android/recaptcha/internal/zzvq;

    new-instance p1, Lcom/google/android/recaptcha/internal/zzvs;

    .line 3
    invoke-direct {p1, v2, v3}, Lcom/google/android/recaptcha/internal/zzvs;-><init>(Lcom/google/android/recaptcha/internal/zzvq;[B)V

    .line 4
    invoke-virtual {v0, p1}, Ljava/util/concurrent/atomic/AtomicReference;->set(Ljava/lang/Object;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method
