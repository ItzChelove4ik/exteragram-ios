.class public final Lcom/google/android/recaptcha/internal/zzlp;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzmg;

.field private final zzb:Ljava/lang/Integer;

.field private final zzc:J

.field private final zzd:I


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzht;Lcom/google/android/recaptcha/internal/zzmg;ILjava/lang/Integer;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzlp;->zza:Lcom/google/android/recaptcha/internal/zzmg;

    iput p3, p0, Lcom/google/android/recaptcha/internal/zzlp;->zzd:I

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzlp;->zzb:Ljava/lang/Integer;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p1

    .line 2
    invoke-static {p1, p2}, Lcom/google/android/recaptcha/internal/zzaiq;->zzc(J)Lcom/google/android/recaptcha/internal/zzahx;

    move-result-object p1

    .line 3
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaiq;->zzb(Lcom/google/android/recaptcha/internal/zzahx;)Ljava/lang/String;

    .line 4
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide p1

    iput-wide p1, p0, Lcom/google/android/recaptcha/internal/zzlp;->zzc:J

    return-void
.end method

.method private final zze(I)Lcom/google/android/recaptcha/internal/zzajh;
    .locals 9

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzajj;->zzd()Lcom/google/android/recaptcha/internal/zzajh;

    move-result-object v0

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzlp;->zzd:I

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzajh;->zzy(I)Lcom/google/android/recaptcha/internal/zzajh;

    .line 3
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzlj;->zzb()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzajh;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajh;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzlp;->zza:Lcom/google/android/recaptcha/internal/zzmg;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzmg;->zzb()Ljava/lang/String;

    move-result-object v2

    .line 4
    invoke-virtual {v0, v2}, Lcom/google/android/recaptcha/internal/zzajh;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajh;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v2

    invoke-virtual {v2}, Lcom/google/android/recaptcha/internal/zzmd;->zzb()Ljava/lang/String;

    move-result-object v2

    .line 5
    invoke-virtual {v0, v2}, Lcom/google/android/recaptcha/internal/zzajh;->zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajh;

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzmg;->zza()Lcom/google/android/recaptcha/internal/zzmd;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzmd;->zzd()I

    move-result v1

    .line 6
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzajh;->zzz(I)Lcom/google/android/recaptcha/internal/zzajh;

    .line 7
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzajh;->zzA(I)Lcom/google/android/recaptcha/internal/zzajh;

    iget-wide v1, p0, Lcom/google/android/recaptcha/internal/zzlp;->zzc:J

    .line 8
    invoke-static {v1, v2}, Lcom/google/android/recaptcha/internal/zzaiq;->zzc(J)Lcom/google/android/recaptcha/internal/zzahx;

    move-result-object p1

    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzajh;->zzh(Lcom/google/android/recaptcha/internal/zzahx;)Lcom/google/android/recaptcha/internal/zzajh;

    .line 9
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    sub-long/2addr v3, v1

    const-wide/16 v1, 0x3e8

    rem-long v5, v3, v1

    const-wide/32 v7, 0xf4240

    mul-long/2addr v5, v7

    div-long/2addr v3, v1

    long-to-int p1, v5

    .line 10
    invoke-static {v3, v4, p1}, Lcom/google/android/recaptcha/internal/zzaio;->zzc(JI)Lcom/google/android/recaptcha/internal/zzafe;

    move-result-object p1

    .line 11
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzajh;->zze(Lcom/google/android/recaptcha/internal/zzafe;)Lcom/google/android/recaptcha/internal/zzajh;

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlp;->zzb:Ljava/lang/Integer;

    if-eqz p0, :cond_0

    .line 12
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzajh;->zzw(I)Lcom/google/android/recaptcha/internal/zzajh;

    :cond_0
    return-object v0
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzmg;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlp;->zza:Lcom/google/android/recaptcha/internal/zzmg;

    return-object p0
.end method

.method public final zzb()V
    .locals 2

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzlp;->zza:Lcom/google/android/recaptcha/internal/zzmg;

    const/4 v1, 0x3

    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzlp;->zze(I)Lcom/google/android/recaptcha/internal/zzajh;

    move-result-object p0

    const/4 v1, 0x0

    .line 2
    invoke-virtual {v0, p0, v1}, Lcom/google/android/recaptcha/internal/zzmg;->zzd(Lcom/google/android/recaptcha/internal/zzajh;Lcom/google/android/recaptcha/internal/zzaja;)V

    return-void
.end method

.method public final zzc(Lcom/google/android/recaptcha/internal/zzhg;)V
    .locals 2

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaja;->zza()Lcom/google/android/recaptcha/internal/zzaiz;

    move-result-object v0

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhg;->zza()Lcom/google/android/recaptcha/internal/zzhf;

    move-result-object v1

    .line 2
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzhf;->zza()I

    move-result v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzaiz;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaiz;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhg;->zzb()Lcom/google/android/recaptcha/internal/zzhe;

    move-result-object v1

    .line 3
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzhe;->zza()I

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzaiz;->zzb(I)Lcom/google/android/recaptcha/internal/zzaiz;

    .line 4
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhg;->zzd()Lcom/google/android/recaptcha/RecaptchaException;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/RecaptchaException;->getErrorCode()Lcom/google/android/recaptcha/RecaptchaErrorCode;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/recaptcha/RecaptchaErrorCode;->getErrorCode()I

    move-result v1

    .line 5
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzaiz;->zzd(I)Lcom/google/android/recaptcha/internal/zzaiz;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzhg;->zzc()Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 6
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzaiz;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaiz;

    :cond_0
    const/4 p1, 0x4

    .line 7
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzlp;->zze(I)Lcom/google/android/recaptcha/internal/zzajh;

    move-result-object p1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlp;->zza:Lcom/google/android/recaptcha/internal/zzmg;

    .line 8
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzaja;

    invoke-virtual {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzmg;->zzd(Lcom/google/android/recaptcha/internal/zzajh;Lcom/google/android/recaptcha/internal/zzaja;)V

    return-void
.end method

.method public final zzd(ILjava/lang/Integer;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
    .locals 1

    .line 1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzmb;

    const/16 p2, 0x36

    const/4 v0, 0x0

    invoke-direct {p1, p2, p3, v0}, Lcom/google/android/recaptcha/internal/zzmb;-><init>(ILkotlin/jvm/functions/Function2;Ljava/lang/Integer;)V

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlp;->zza:Lcom/google/android/recaptcha/internal/zzmg;

    invoke-virtual {p1, p0, p4}, Lcom/google/android/recaptcha/internal/zzmb;->zza(Lcom/google/android/recaptcha/internal/zzmg;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
