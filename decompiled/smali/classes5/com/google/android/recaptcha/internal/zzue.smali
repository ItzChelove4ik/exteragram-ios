.class public abstract Lcom/google/android/recaptcha/internal/zzue;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/Class;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/Class;[B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzue;->zza:Ljava/lang/Class;

    return-void
.end method

.method public static zzc(Lcom/google/android/recaptcha/internal/zzud;Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzue;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzuc;

    invoke-direct {v0, p1, p0}, Lcom/google/android/recaptcha/internal/zzuc;-><init>(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzud;)V

    return-object v0
.end method


# virtual methods
.method public abstract zza(Lcom/google/android/recaptcha/internal/zzse;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;
.end method

.method public final zzb()Ljava/lang/Class;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzue;->zza:Ljava/lang/Class;

    return-object p0
.end method
