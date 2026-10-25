.class public abstract Lcom/google/android/recaptcha/internal/zzsx;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zza:Lcom/google/android/recaptcha/internal/zzsw;

.field private static final zzb:Lcom/google/android/recaptcha/internal/zzsc;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsw;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzsw;-><init>([B)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsx;->zza:Lcom/google/android/recaptcha/internal/zzsw;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzsv;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzsv;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsx;->zzb:Lcom/google/android/recaptcha/internal/zzsc;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzsc;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzsx;->zzb:Lcom/google/android/recaptcha/internal/zzsc;

    return-object v0
.end method

.method public static synthetic zzb()Lcom/google/android/recaptcha/internal/zzsw;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzsx;->zza:Lcom/google/android/recaptcha/internal/zzsw;

    return-object v0
.end method
