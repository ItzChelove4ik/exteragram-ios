.class final Lcom/google/android/gms/internal/location/zzdu;
.super Lcom/google/android/gms/location/zzu;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/gms/internal/location/zzdq;


# direct methods
.method public constructor <init>(Lcom/google/android/gms/internal/location/zzdq;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/location/zzu;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/location/zzdu;->zza:Lcom/google/android/gms/internal/location/zzdq;

    return-void
.end method


# virtual methods
.method public final zzc(Lcom/google/android/gms/common/api/internal/ListenerHolder;)Lcom/google/android/gms/internal/location/zzdu;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/location/zzdu;->zza:Lcom/google/android/gms/internal/location/zzdq;

    invoke-interface {v0, p1}, Lcom/google/android/gms/internal/location/zzdq;->zzb(Lcom/google/android/gms/common/api/internal/ListenerHolder;)V

    return-object p0
.end method

.method public final zzd(Lcom/google/android/gms/location/LocationResult;)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/location/zzdu;->zza:Lcom/google/android/gms/internal/location/zzdq;

    invoke-interface {v0}, Lcom/google/android/gms/internal/location/zzdq;->zza()Lcom/google/android/gms/common/api/internal/ListenerHolder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/location/zzdr;

    invoke-direct {v1, p0, p1}, Lcom/google/android/gms/internal/location/zzdr;-><init>(Lcom/google/android/gms/internal/location/zzdu;Lcom/google/android/gms/location/LocationResult;)V

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/common/api/internal/ListenerHolder;->notifyListener(Lcom/google/android/gms/common/api/internal/ListenerHolder$Notifier;)V

    return-void
.end method

.method public final zze(Lcom/google/android/gms/location/LocationAvailability;)V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/location/zzdu;->zza:Lcom/google/android/gms/internal/location/zzdq;

    invoke-interface {v0}, Lcom/google/android/gms/internal/location/zzdq;->zza()Lcom/google/android/gms/common/api/internal/ListenerHolder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/location/zzds;

    invoke-direct {v1, p0, p1}, Lcom/google/android/gms/internal/location/zzds;-><init>(Lcom/google/android/gms/internal/location/zzdu;Lcom/google/android/gms/location/LocationAvailability;)V

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/common/api/internal/ListenerHolder;->notifyListener(Lcom/google/android/gms/common/api/internal/ListenerHolder$Notifier;)V

    return-void
.end method

.method public final zzf()V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/location/zzdu;->zza:Lcom/google/android/gms/internal/location/zzdq;

    invoke-interface {v0}, Lcom/google/android/gms/internal/location/zzdq;->zza()Lcom/google/android/gms/common/api/internal/ListenerHolder;

    move-result-object v0

    new-instance v1, Lcom/google/android/gms/internal/location/zzdt;

    invoke-direct {v1, p0}, Lcom/google/android/gms/internal/location/zzdt;-><init>(Lcom/google/android/gms/internal/location/zzdu;)V

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/common/api/internal/ListenerHolder;->notifyListener(Lcom/google/android/gms/common/api/internal/ListenerHolder$Notifier;)V

    return-void
.end method

.method public final zzg()V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/gms/internal/location/zzdu;->zza:Lcom/google/android/gms/internal/location/zzdq;

    invoke-interface {p0}, Lcom/google/android/gms/internal/location/zzdq;->zza()Lcom/google/android/gms/common/api/internal/ListenerHolder;

    move-result-object p0

    invoke-virtual {p0}, Lcom/google/android/gms/common/api/internal/ListenerHolder;->clear()V

    return-void
.end method

.method public final synthetic zzh()Lcom/google/android/gms/internal/location/zzdq;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/location/zzdu;->zza:Lcom/google/android/gms/internal/location/zzdq;

    return-object p0
.end method
