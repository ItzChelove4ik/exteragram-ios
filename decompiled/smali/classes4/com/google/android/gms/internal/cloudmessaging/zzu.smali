.class public abstract Lcom/google/android/gms/internal/cloudmessaging/zzu;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/gms/internal/cloudmessaging/zzs;

.field private static volatile zzb:Lcom/google/android/gms/internal/cloudmessaging/zzs;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/gms/internal/cloudmessaging/zzt;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/gms/internal/cloudmessaging/zzt;-><init>([B)V

    sput-object v0, Lcom/google/android/gms/internal/cloudmessaging/zzu;->zza:Lcom/google/android/gms/internal/cloudmessaging/zzs;

    sput-object v0, Lcom/google/android/gms/internal/cloudmessaging/zzu;->zzb:Lcom/google/android/gms/internal/cloudmessaging/zzs;

    return-void
.end method

.method public static zza()Lcom/google/android/gms/internal/cloudmessaging/zzs;
    .locals 1

    sget-object v0, Lcom/google/android/gms/internal/cloudmessaging/zzu;->zzb:Lcom/google/android/gms/internal/cloudmessaging/zzs;

    return-object v0
.end method
