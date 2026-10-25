.class public final Lcom/google/android/recaptcha/internal/zzvb;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzvb;


# instance fields
.field private final zzb:Ljava/util/concurrent/atomic/AtomicReference;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzva;->zza:Lcom/google/android/recaptcha/internal/zzva;

    invoke-static {v0}, Lcom/google/android/recaptcha/internal/zzwb;->zza(Lcom/google/android/recaptcha/internal/zzwa;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzvb;

    sput-object v0, Lcom/google/android/recaptcha/internal/zzvb;->zza:Lcom/google/android/recaptcha/internal/zzvb;

    return-void
.end method

.method public constructor <init>()V
    .locals 4

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/concurrent/atomic/AtomicReference;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzvx;

    invoke-direct {v1}, Lcom/google/android/recaptcha/internal/zzvx;-><init>()V

    new-instance v2, Lcom/google/android/recaptcha/internal/zzvy;

    const/4 v3, 0x0

    .line 2
    invoke-direct {v2, v1, v3}, Lcom/google/android/recaptcha/internal/zzvy;-><init>(Lcom/google/android/recaptcha/internal/zzvx;[B)V

    .line 3
    invoke-direct {v0, v2}, Ljava/util/concurrent/atomic/AtomicReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzvb;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzvb;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzvb;->zza:Lcom/google/android/recaptcha/internal/zzvb;

    return-object v0
.end method


# virtual methods
.method public final declared-synchronized zzb(Lcom/google/android/recaptcha/internal/zzue;)V
    .locals 3

    monitor-enter p0

    .line 1
    :try_start_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzvx;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzvb;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzvy;

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzvx;-><init>(Lcom/google/android/recaptcha/internal/zzvy;)V

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzvx;->zza(Lcom/google/android/recaptcha/internal/zzue;)Lcom/google/android/recaptcha/internal/zzvx;

    new-instance p1, Lcom/google/android/recaptcha/internal/zzvy;

    const/4 v2, 0x0

    .line 2
    invoke-direct {p1, v0, v2}, Lcom/google/android/recaptcha/internal/zzvy;-><init>(Lcom/google/android/recaptcha/internal/zzvx;[B)V

    .line 3
    invoke-virtual {v1, p1}, Ljava/util/concurrent/atomic/AtomicReference;->set(Ljava/lang/Object;)V
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

.method public final declared-synchronized zzc(Lcom/google/android/recaptcha/internal/zzub;)V
    .locals 3

    monitor-enter p0

    .line 1
    :try_start_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzvx;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzvb;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzvy;

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzvx;-><init>(Lcom/google/android/recaptcha/internal/zzvy;)V

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzvx;->zzb(Lcom/google/android/recaptcha/internal/zzub;)Lcom/google/android/recaptcha/internal/zzvx;

    new-instance p1, Lcom/google/android/recaptcha/internal/zzvy;

    const/4 v2, 0x0

    .line 2
    invoke-direct {p1, v0, v2}, Lcom/google/android/recaptcha/internal/zzvy;-><init>(Lcom/google/android/recaptcha/internal/zzvx;[B)V

    .line 3
    invoke-virtual {v1, p1}, Ljava/util/concurrent/atomic/AtomicReference;->set(Ljava/lang/Object;)V
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

.method public final declared-synchronized zzd(Lcom/google/android/recaptcha/internal/zzvi;)V
    .locals 3

    monitor-enter p0

    .line 1
    :try_start_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzvx;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzvb;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzvy;

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzvx;-><init>(Lcom/google/android/recaptcha/internal/zzvy;)V

    .line 2
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzvx;->zzc(Lcom/google/android/recaptcha/internal/zzvi;)Lcom/google/android/recaptcha/internal/zzvx;

    new-instance p1, Lcom/google/android/recaptcha/internal/zzvy;

    const/4 v2, 0x0

    .line 3
    invoke-direct {p1, v0, v2}, Lcom/google/android/recaptcha/internal/zzvy;-><init>(Lcom/google/android/recaptcha/internal/zzvx;[B)V

    .line 4
    invoke-virtual {v1, p1}, Ljava/util/concurrent/atomic/AtomicReference;->set(Ljava/lang/Object;)V
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

.method public final declared-synchronized zze(Lcom/google/android/recaptcha/internal/zzvf;)V
    .locals 3

    monitor-enter p0

    .line 1
    :try_start_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzvx;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzvb;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    invoke-virtual {v1}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/android/recaptcha/internal/zzvy;

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zzvx;-><init>(Lcom/google/android/recaptcha/internal/zzvy;)V

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzvx;->zzd(Lcom/google/android/recaptcha/internal/zzvf;)Lcom/google/android/recaptcha/internal/zzvx;

    new-instance p1, Lcom/google/android/recaptcha/internal/zzvy;

    const/4 v2, 0x0

    .line 2
    invoke-direct {p1, v0, v2}, Lcom/google/android/recaptcha/internal/zzvy;-><init>(Lcom/google/android/recaptcha/internal/zzvx;[B)V

    .line 3
    invoke-virtual {v1, p1}, Ljava/util/concurrent/atomic/AtomicReference;->set(Ljava/lang/Object;)V
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

.method public final zzf(Lcom/google/android/recaptcha/internal/zzsp;)Z
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvb;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzvy;

    invoke-virtual {p0, p1}, Lcom/google/android/recaptcha/internal/zzvy;->zzc(Lcom/google/android/recaptcha/internal/zzsp;)Z

    move-result p0

    return p0
.end method

.method public final zzg(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzse;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvb;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzvy;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzvy;->zza(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzse;

    move-result-object p0

    return-object p0
.end method

.method public final zzh(Lcom/google/android/recaptcha/internal/zzse;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzvb;->zzb:Ljava/util/concurrent/atomic/AtomicReference;

    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicReference;->get()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzvy;

    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzvy;->zzb(Lcom/google/android/recaptcha/internal/zzse;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;

    move-result-object p0

    return-object p0
.end method
