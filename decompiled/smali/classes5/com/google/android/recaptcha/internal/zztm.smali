.class final Lcom/google/android/recaptcha/internal/zztm;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field final zza:Lcom/google/android/recaptcha/internal/zztn;

.field final zzb:[J


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zztm;)V
    .locals 2

    .line 2
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Lcom/google/android/recaptcha/internal/zztn;

    iget-object v1, p1, Lcom/google/android/recaptcha/internal/zztm;->zza:Lcom/google/android/recaptcha/internal/zztn;

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zztn;-><init>(Lcom/google/android/recaptcha/internal/zztn;)V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zztm;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iget-object p1, p1, Lcom/google/android/recaptcha/internal/zztm;->zzb:[J

    const/16 v0, 0xa

    .line 3
    invoke-static {p1, v0}, Ljava/util/Arrays;->copyOf([JI)[J

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zztm;->zzb:[J

    return-void
.end method

.method public constructor <init>(Lcom/google/android/recaptcha/internal/zztn;[J)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zztm;->zza:Lcom/google/android/recaptcha/internal/zztn;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zztm;->zzb:[J

    return-void
.end method
