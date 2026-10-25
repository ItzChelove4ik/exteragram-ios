.class final Lcom/google/android/recaptcha/internal/zzaas;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzsc;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final zza(Lcom/google/android/recaptcha/internal/zzsk;Ljava/lang/Class;)Ljava/lang/Object;
    .locals 0

    .line 1
    const-class p0, Lcom/google/android/recaptcha/internal/zzss;

    if-ne p0, p2, :cond_0

    sget-object p0, Lcom/google/android/recaptcha/internal/zzaar;->zza:Lcom/google/android/recaptcha/internal/zzaar;

    invoke-static {p1, p0}, Lcom/google/android/recaptcha/internal/zzacq;->zza(Lcom/google/android/recaptcha/internal/zzsk;Lcom/google/android/recaptcha/internal/zzvt;)Lcom/google/android/recaptcha/internal/zzss;

    move-result-object p0

    .line 2
    invoke-virtual {p2, p0}, Ljava/lang/Class;->cast(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    :cond_0
    sget-object p0, Lcom/google/android/recaptcha/internal/zzaaq;->zza:Lcom/google/android/recaptcha/internal/zzaaq;

    .line 3
    invoke-static {p1, p0}, Lcom/google/android/recaptcha/internal/zzact;->zza(Lcom/google/android/recaptcha/internal/zzsk;Lcom/google/android/recaptcha/internal/zzvt;)Lcom/google/android/recaptcha/internal/zzst;

    move-result-object p0

    .line 4
    invoke-virtual {p2, p0}, Ljava/lang/Class;->cast(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
