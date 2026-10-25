.class public final Lcom/google/android/recaptcha/internal/zzui;
.super Lcom/google/android/recaptcha/internal/zzse;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzsp;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzse;-><init>()V

    invoke-static {p1, p2}, Lcom/google/android/recaptcha/internal/zzui;->zze(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzui;->zza:Lcom/google/android/recaptcha/internal/zzsp;

    return-void
.end method

.method private static zze(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)V
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzd()Lcom/google/android/recaptcha/internal/zzsn;

    move-result-object p1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzsn;->zzb:Lcom/google/android/recaptcha/internal/zzsn;

    invoke-virtual {p1, v0}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzd()Lcom/google/android/recaptcha/internal/zzsn;

    move-result-object p0

    .line 2
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    :cond_0
    return-void
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzsl;
    .locals 3

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzui;->zza:Lcom/google/android/recaptcha/internal/zzsp;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzuh;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzb()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object p0

    const/4 v2, 0x0

    invoke-direct {v0, v1, p0, v2}, Lcom/google/android/recaptcha/internal/zzuh;-><init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzso;[B)V

    return-object v0
.end method

.method public final zzb()Ljava/lang/Integer;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzui;->zza:Lcom/google/android/recaptcha/internal/zzsp;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzf()Ljava/lang/Integer;

    move-result-object p0

    return-object p0
.end method

.method public final zzc(Lcom/google/android/recaptcha/internal/zzsy;)Lcom/google/android/recaptcha/internal/zzsp;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzui;->zza:Lcom/google/android/recaptcha/internal/zzsp;

    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzui;->zze(Lcom/google/android/recaptcha/internal/zzsp;Lcom/google/android/recaptcha/internal/zzsy;)V

    return-object p0
.end method

.method public final zzd()Lcom/google/android/recaptcha/internal/zzady;
    .locals 2

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzui;->zza:Lcom/google/android/recaptcha/internal/zzsp;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzso;->zzd:Lcom/google/android/recaptcha/internal/zzso;

    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p0, 0x0

    new-array p0, p0, [B

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzady;->zza([B)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object p0

    return-object p0

    :cond_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzso;->zzb:Lcom/google/android/recaptcha/internal/zzso;

    .line 3
    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzf()Ljava/lang/Integer;

    move-result-object p0

    .line 4
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzvc;->zzb(I)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object p0

    return-object p0

    :cond_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzso;->zzc:Lcom/google/android/recaptcha/internal/zzso;

    .line 5
    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zze()Lcom/google/android/recaptcha/internal/zzso;

    move-result-object v0

    sget-object v1, Lcom/google/android/recaptcha/internal/zzso;->zze:Lcom/google/android/recaptcha/internal/zzso;

    .line 6
    invoke-virtual {v0, v1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    goto :goto_0

    .line 7
    :cond_2
    const-string p0, "Unknown output prefix type"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :cond_3
    :goto_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzsp;->zzf()Ljava/lang/Integer;

    move-result-object p0

    .line 8
    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzvc;->zza(I)Lcom/google/android/recaptcha/internal/zzady;

    move-result-object p0

    return-object p0
.end method
