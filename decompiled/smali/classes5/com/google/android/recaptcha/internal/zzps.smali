.class final Lcom/google/android/recaptcha/internal/zzps;
.super Lcom/google/android/recaptcha/internal/zzpt;
.source "SourceFile"


# instance fields
.field final transient zza:I

.field final transient zzb:I

.field final synthetic zzc:Lcom/google/android/recaptcha/internal/zzpt;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzpt;II)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzps;->zzc:Lcom/google/android/recaptcha/internal/zzpt;

    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzpt;-><init>()V

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzps;->zza:I

    iput p3, p0, Lcom/google/android/recaptcha/internal/zzps;->zzb:I

    return-void
.end method


# virtual methods
.method public final get(I)Ljava/lang/Object;
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzps;->zzb:I

    const-string v1, "index"

    invoke-static {p1, v0, v1}, Lcom/google/android/recaptcha/internal/zzoq;->zze(IILjava/lang/String;)I

    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzps;->zzc:Lcom/google/android/recaptcha/internal/zzpt;

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzps;->zza:I

    add-int/2addr p1, p0

    .line 2
    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final size()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzps;->zzb:I

    return p0
.end method

.method public final bridge synthetic subList(II)Ljava/util/List;
    .locals 0

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzpt;->zzg(II)Lcom/google/android/recaptcha/internal/zzpt;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()[Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzps;->zzc:Lcom/google/android/recaptcha/internal/zzpt;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzpq;->zzb()[Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final zzc()I
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzps;->zzc:Lcom/google/android/recaptcha/internal/zzpt;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzpq;->zzc()I

    move-result v0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzps;->zza:I

    add-int/2addr v0, p0

    return v0
.end method

.method public final zzd()I
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzps;->zzc:Lcom/google/android/recaptcha/internal/zzpt;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzpq;->zzc()I

    move-result v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzps;->zza:I

    add-int/2addr v0, v1

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzps;->zzb:I

    add-int/2addr v0, p0

    return v0
.end method

.method public final zzg(II)Lcom/google/android/recaptcha/internal/zzpt;
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzps;->zzb:I

    invoke-static {p1, p2, v0}, Lcom/google/android/recaptcha/internal/zzoq;->zzg(III)V

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzps;->zza:I

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzps;->zzc:Lcom/google/android/recaptcha/internal/zzpt;

    add-int/2addr p1, v0

    add-int/2addr p2, v0

    .line 2
    invoke-virtual {p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzpt;->zzg(II)Lcom/google/android/recaptcha/internal/zzpt;

    move-result-object p0

    return-object p0
.end method
