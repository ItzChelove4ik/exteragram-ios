.class final Lcom/google/android/recaptcha/internal/zzto;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field final zza:Lcom/google/android/recaptcha/internal/zztn;

.field final zzb:[J


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zztn;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zztn;-><init>()V

    const/16 v1, 0xa

    new-array v1, v1, [J

    invoke-direct {p0, v0, v1}, Lcom/google/android/recaptcha/internal/zzto;-><init>(Lcom/google/android/recaptcha/internal/zztn;[J)V

    return-void
.end method

.method public constructor <init>(Lcom/google/android/recaptcha/internal/zztm;)V
    .locals 0

    .line 2
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzto;-><init>()V

    .line 3
    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzto;->zzb(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/recaptcha/internal/zztn;[J)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzto;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzto;->zzb:[J

    return-void
.end method

.method public static synthetic zza(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;
    .locals 0

    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzto;->zzb(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;

    return-object p0
.end method

.method private static zzb(Lcom/google/android/recaptcha/internal/zzto;Lcom/google/android/recaptcha/internal/zztm;)Lcom/google/android/recaptcha/internal/zzto;
    .locals 5

    .line 1
    iget-object v0, p1, Lcom/google/android/recaptcha/internal/zztm;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzto;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iget-object v2, v1, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    iget-object v3, v0, Lcom/google/android/recaptcha/internal/zztn;->zza:[J

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zztm;->zzb:[J

    invoke-static {v2, v3, p1}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    iget-object v2, v1, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    iget-object v4, v0, Lcom/google/android/recaptcha/internal/zztn;->zzb:[J

    iget-object v0, v0, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    .line 2
    invoke-static {v2, v4, v0}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    iget-object v1, v1, Lcom/google/android/recaptcha/internal/zztn;->zzc:[J

    .line 3
    invoke-static {v1, v0, p1}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzto;->zzb:[J

    .line 4
    invoke-static {p1, v3, v4}, Lcom/google/android/recaptcha/internal/zztw;->zze([J[J[J)V

    return-object p0
.end method
