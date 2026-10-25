.class public abstract Lcom/google/android/gms/internal/cloudmessaging/zzn;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static volatile zza:Lcom/google/android/gms/internal/cloudmessaging/zzo;

.field private static volatile zzb:Lcom/google/android/gms/internal/cloudmessaging/zzp;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/cloudmessaging/zzo;

    invoke-direct {v0}, Lcom/google/android/gms/internal/cloudmessaging/zzo;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/cloudmessaging/zzn;->zza:Lcom/google/android/gms/internal/cloudmessaging/zzo;

    new-instance v0, Lcom/google/android/gms/internal/cloudmessaging/zzp;

    invoke-direct {v0}, Lcom/google/android/gms/internal/cloudmessaging/zzp;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/cloudmessaging/zzn;->zzb:Lcom/google/android/gms/internal/cloudmessaging/zzp;

    return-void
.end method

.method public static zza()Lcom/google/android/gms/internal/cloudmessaging/zzo;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/cloudmessaging/zzn;->zza:Lcom/google/android/gms/internal/cloudmessaging/zzo;

    return-object v0
.end method
