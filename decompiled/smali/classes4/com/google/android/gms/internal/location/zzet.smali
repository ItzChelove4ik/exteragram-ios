.class final Lcom/google/android/gms/internal/location/zzet;
.super Lcom/google/android/gms/internal/location/zzeu;
.source "SourceFile"


# instance fields
.field final transient zza:I

.field final transient zzb:I

.field final synthetic zzc:Lcom/google/android/gms/internal/location/zzeu;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/location/zzeu;II)V
    .locals 0

    .line 1
    invoke-static {p1}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    iput-object p1, p0, Lcom/google/android/gms/internal/location/zzet;->zzc:Lcom/google/android/gms/internal/location/zzeu;

    invoke-direct {p0}, Lcom/google/android/gms/internal/location/zzeu;-><init>()V

    iput p2, p0, Lcom/google/android/gms/internal/location/zzet;->zza:I

    iput p3, p0, Lcom/google/android/gms/internal/location/zzet;->zzb:I

    return-void
.end method


# virtual methods
.method public final get(I)Ljava/lang/Object;
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/gms/internal/location/zzet;->zzb:I

    const-string v1, "index"

    invoke-static {p1, v0, v1}, Lcom/google/android/gms/internal/location/zzeo;->zzb(IILjava/lang/String;)I

    iget-object v0, p0, Lcom/google/android/gms/internal/location/zzet;->zzc:Lcom/google/android/gms/internal/location/zzeu;

    iget p0, p0, Lcom/google/android/gms/internal/location/zzet;->zza:I

    add-int/2addr p1, p0

    .line 2
    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final size()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/location/zzet;->zzb:I

    return p0
.end method

.method public final bridge synthetic subList(II)Ljava/util/List;
    .locals 0

    .line 1
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/location/zzeu;->zzh(II)Lcom/google/android/gms/internal/location/zzeu;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()[Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/location/zzet;->zzc:Lcom/google/android/gms/internal/location/zzeu;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/location/zzer;->zzb()[Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public final zzc()I
    .locals 1

    iget-object v0, p0, Lcom/google/android/gms/internal/location/zzet;->zzc:Lcom/google/android/gms/internal/location/zzeu;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/location/zzer;->zzc()I

    move-result v0

    iget p0, p0, Lcom/google/android/gms/internal/location/zzet;->zza:I

    add-int/2addr v0, p0

    return v0
.end method

.method public final zzd()I
    .locals 2

    iget-object v0, p0, Lcom/google/android/gms/internal/location/zzet;->zzc:Lcom/google/android/gms/internal/location/zzeu;

    invoke-virtual {v0}, Lcom/google/android/gms/internal/location/zzer;->zzc()I

    move-result v0

    iget v1, p0, Lcom/google/android/gms/internal/location/zzet;->zza:I

    add-int/2addr v0, v1

    iget p0, p0, Lcom/google/android/gms/internal/location/zzet;->zzb:I

    add-int/2addr v0, p0

    return v0
.end method

.method public final zzf()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public final zzh(II)Lcom/google/android/gms/internal/location/zzeu;
    .locals 1

    .line 1
    iget v0, p0, Lcom/google/android/gms/internal/location/zzet;->zzb:I

    invoke-static {p1, p2, v0}, Lcom/google/android/gms/internal/location/zzeo;->zzd(III)V

    iget v0, p0, Lcom/google/android/gms/internal/location/zzet;->zza:I

    iget-object p0, p0, Lcom/google/android/gms/internal/location/zzet;->zzc:Lcom/google/android/gms/internal/location/zzeu;

    add-int/2addr p1, v0

    add-int/2addr p2, v0

    .line 2
    invoke-virtual {p0, p1, p2}, Lcom/google/android/gms/internal/location/zzeu;->zzh(II)Lcom/google/android/gms/internal/location/zzeu;

    move-result-object p0

    return-object p0
.end method
