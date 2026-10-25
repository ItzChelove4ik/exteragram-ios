.class public abstract Lcom/google/android/recaptcha/internal/zzub;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Ljava/lang/String;


# direct methods
.method public synthetic constructor <init>(Ljava/lang/String;[B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzub;->zza:Ljava/lang/String;

    return-void
.end method

.method public static zzc(Lcom/google/android/recaptcha/internal/zzua;Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzub;
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zztz;

    invoke-direct {v0, p1, p0}, Lcom/google/android/recaptcha/internal/zztz;-><init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzua;)V

    return-object v0
.end method


# virtual methods
.method public abstract zza(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzse;
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzub;->zza:Ljava/lang/String;

    return-object p0
.end method
