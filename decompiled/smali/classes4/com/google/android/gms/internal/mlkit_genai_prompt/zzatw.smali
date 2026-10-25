.class final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/mlkit/genai/common/StreamingCallback;


# instance fields
.field final synthetic zza:Ljava/util/concurrent/atomic/AtomicLong;

.field final synthetic zzb:J

.field final synthetic zzc:Lcom/google/mlkit/genai/common/StreamingCallback;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaun;Ljava/util/concurrent/atomic/AtomicLong;JLcom/google/mlkit/genai/common/StreamingCallback;)V
    .locals 0

    .line 1
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;->zza:Ljava/util/concurrent/atomic/AtomicLong;

    iput-wide p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;->zzb:J

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;->zzc:Lcom/google/mlkit/genai/common/StreamingCallback;

    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final onNewText(Ljava/lang/String;)V
    .locals 5

    .line 1
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v0

    iget-wide v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;->zzb:J

    sub-long/2addr v0, v2

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;->zza:Ljava/util/concurrent/atomic/AtomicLong;

    const-wide/16 v3, 0x0

    invoke-virtual {v2, v3, v4, v0, v1}, Ljava/util/concurrent/atomic/AtomicLong;->compareAndSet(JJ)Z

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;->zzc:Lcom/google/mlkit/genai/common/StreamingCallback;

    .line 2
    invoke-interface {p0, p1}, Lcom/google/mlkit/genai/common/StreamingCallback;->onNewText(Ljava/lang/String;)V

    return-void
.end method

.method public final onNewThought(Ljava/lang/String;)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzatw;->zzc:Lcom/google/mlkit/genai/common/StreamingCallback;

    invoke-interface {p0, p1}, Lcom/google/mlkit/genai/common/StreamingCallback;->onNewThought(Ljava/lang/String;)V

    return-void
.end method
