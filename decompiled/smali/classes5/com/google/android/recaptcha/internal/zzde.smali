.class final synthetic Lcom/google/android/recaptcha/internal/zzde;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lkotlin/jvm/functions/Function1;


# instance fields
.field private final synthetic zza:Lcom/google/android/recaptcha/internal/zzcz;

.field private final synthetic zzb:Lcom/google/android/recaptcha/internal/zzali;


# direct methods
.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzcz;Lcom/google/android/recaptcha/internal/zzali;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzde;->zza:Lcom/google/android/recaptcha/internal/zzcz;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzde;->zzb:Lcom/google/android/recaptcha/internal/zzali;

    return-void
.end method


# virtual methods
.method public final synthetic invoke(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    check-cast p1, Lcom/google/android/recaptcha/internal/zzaka;

    .line 1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaka;->zza()Ljava/lang/String;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzde;->zza:Lcom/google/android/recaptcha/internal/zzcz;

    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzcz;->zza(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzde;->zzb:Lcom/google/android/recaptcha/internal/zzali;

    .line 2
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object p1

    check-cast p1, Lcom/google/android/recaptcha/internal/zzajz;

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzali;->zza()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Lcom/google/android/recaptcha/internal/zzajz;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajz;

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzali;->zzb()Lcom/google/android/recaptcha/internal/zzaer;

    move-result-object p0

    invoke-virtual {p1, p0}, Lcom/google/android/recaptcha/internal/zzajz;->zzb(Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzajz;

    .line 5
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzaka;

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method
