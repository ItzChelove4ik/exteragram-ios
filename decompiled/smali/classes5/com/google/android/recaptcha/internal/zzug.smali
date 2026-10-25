.class public Lcom/google/android/recaptcha/internal/zzug;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzsf;


# instance fields
.field final zza:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/Class;ILcom/google/android/recaptcha/internal/zzahe;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzug;->zza:Ljava/lang/String;

    return-void
.end method

.method public static zzb(Ljava/lang/String;Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsm;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzuf;

    invoke-direct {v0, p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzuf;-><init>(Ljava/lang/String;Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzahe;)V

    return-object v0
.end method

.method public static zzc(Ljava/lang/String;Ljava/lang/Class;ILcom/google/android/recaptcha/internal/zzahe;)Lcom/google/android/recaptcha/internal/zzsf;
    .locals 1

    new-instance p2, Lcom/google/android/recaptcha/internal/zzug;

    const/4 v0, 0x5

    invoke-direct {p2, p0, p1, v0, p3}, Lcom/google/android/recaptcha/internal/zzug;-><init>(Ljava/lang/String;Ljava/lang/Class;ILcom/google/android/recaptcha/internal/zzahe;)V

    return-object p2
.end method


# virtual methods
.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzug;->zza:Ljava/lang/String;

    return-object p0
.end method
