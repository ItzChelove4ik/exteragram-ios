.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzx()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhh;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhh;-><init>()V

    const v1, 0x3dcccccd    # 0.1f

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhh;->zzb(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    const/4 v1, 0x3

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzc(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 3
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zze(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    const/4 v1, 0x0

    .line 4
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzf(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 5
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzg(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 6
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzk(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 7
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzl(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 8
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    const/4 v2, 0x1

    .line 9
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzj(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 10
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzh(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    const/16 v2, 0x7b

    .line 11
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;

    .line 12
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzo(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 13
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzp(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    const/high16 v2, 0x3f800000    # 1.0f

    .line 14
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzd(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    .line 15
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzs(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

    .line 16
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;->zzt(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhx;

    return-object v0
.end method


# virtual methods
.method public abstract zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method

.method public abstract zzb()F
.end method

.method public abstract zzc()I
.end method

.method public abstract zzd()F
.end method

.method public abstract zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method

.method public abstract zzf()I
.end method

.method public abstract zzg()I
.end method

.method public abstract zzh()I
.end method

.method public abstract zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;
.end method

.method public abstract zzj()Z
.end method

.method public abstract zzk()I
.end method

.method public abstract zzl()I
.end method

.method public abstract zzm()I
.end method

.method public abstract zzn()Ljava/io/File;
.end method

.method public abstract zzo()Ljava/io/File;
.end method

.method public abstract zzp()Ljava/io/File;
.end method

.method public abstract zzq()I
.end method

.method public abstract zzr()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzeb;
.end method

.method public abstract zzs()I
.end method

.method public abstract zzt()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;
.end method

.method public abstract zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;
.end method

.method public abstract zzv()Z
.end method

.method public abstract zzw()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;
.end method

.method public final zzy(Landroid/content/Context;Ljava/util/concurrent/Executor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;
    .locals 9

    .line 1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    move-result-object p1

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzt()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;

    move-result-object p2

    const/4 v0, 0x0

    const/4 v1, 0x0

    if-eqz p2, :cond_4

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;->zzb()Landroid/os/ParcelFileDescriptor;

    move-result-object v2

    const-string v3, "Cache file should not be null"

    .line 2
    invoke-static {v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzla;->zzd(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 3
    invoke-virtual {v2}, Landroid/os/ParcelFileDescriptor;->getStatSize()J

    move-result-wide v3

    .line 4
    invoke-virtual {v2}, Landroid/os/ParcelFileDescriptor;->getStatSize()J

    move-result-wide v5

    const-wide/32 v7, 0xc800000

    cmp-long v3, v3, v7

    if-gtz v3, :cond_3

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhu;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p2

    .line 5
    invoke-virtual {p2}, Ljava/util/AbstractCollection;->size()I

    move-result v3

    const/4 v4, 0x1

    if-gt v3, v4, :cond_0

    goto :goto_0

    :cond_0
    move v4, v1

    :goto_0
    const-string v3, "Only single message is supported for now."

    invoke-static {v4, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzla;->zza(ZLjava/lang/Object;)V

    new-instance v3, Ljava/util/ArrayList;

    .line 6
    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    .line 7
    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v4

    move v5, v1

    :goto_1
    if-ge v5, v4, :cond_2

    invoke-interface {p2, v5}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    .line 8
    check-cast v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;

    .line 9
    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzh()I

    move-result v7

    if-nez v7, :cond_1

    .line 10
    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zza()I

    move-result v7

    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzb()Ljava/lang/String;

    move-result-object v6

    .line 63
    invoke-virtual {v6}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 11
    invoke-static {v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzd(ILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;

    move-result-object v6

    .line 12
    invoke-interface {v3, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v5, v5, 0x1

    goto :goto_1

    .line 64
    :cond_1
    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzh()I

    move-result p0

    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p1

    add-int/lit8 p1, p1, 0x34

    const-string p2, "Only TEXT type is supported for now. Received type: "

    invoke-static {p1, p2, p0}, Lcom/google/android/gms/cast/internal/zzar$$ExternalSyntheticBUOutline1;->m(ILjava/lang/Object;I)V

    return-object v0

    .line 13
    :cond_2
    invoke-static {v3, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;->zzb(Ljava/util/List;Landroid/os/ParcelFileDescriptor;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;

    move-result-object p2

    .line 14
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzp(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    goto :goto_2

    :cond_3
    const/high16 p0, 0xc800000

    .line 65
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    filled-new-array {p0, p1}, [Ljava/lang/Object;

    move-result-object p0

    const-string p1, "Cache file should be less than %s bytes: %s"

    invoke-static {p1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzlb;->zzd(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-object v0

    .line 14
    :cond_4
    :goto_2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move-result-object p2

    if-eqz p2, :cond_5

    .line 15
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzw()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzs(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    :cond_5
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;

    move-result-object p2

    if-eqz p2, :cond_6

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhw;

    .line 16
    invoke-direct {v2, p0, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhw;-><init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzik;)V

    .line 17
    invoke-virtual {p1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbl;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    :cond_6
    new-instance p2, Landroid/os/Bundle;

    .line 18
    invoke-direct {p2}, Landroid/os/Bundle;-><init>()V

    .line 19
    sget v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->$r8$clinit:I

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    .line 20
    invoke-direct {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;-><init>()V

    new-instance v3, Ljava/util/ArrayList;

    .line 21
    invoke-direct {v3}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/List;->size()I

    move-result v4

    move v5, v1

    :goto_3
    if-ge v5, v4, :cond_9

    invoke-interface {v3, v5}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    .line 22
    check-cast v6, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;

    .line 23
    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzh()I

    move-result v7

    if-eqz v7, :cond_8

    .line 24
    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zze()Landroid/net/Uri;

    .line 25
    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zza()I

    move-result v7

    .line 26
    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzc()Landroid/graphics/Bitmap;

    move-result-object v6

    if-nez v6, :cond_7

    move-object v6, v0

    goto :goto_4

    .line 27
    :cond_7
    invoke-virtual {v6}, Landroid/graphics/Bitmap;->asShared()Landroid/graphics/Bitmap;

    move-result-object v6

    .line 32
    :goto_4
    invoke-virtual {v6}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 28
    invoke-static {v7, v6, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzc(ILandroid/graphics/Bitmap;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;

    move-result-object v6

    .line 29
    invoke-virtual {v2, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zzd(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    goto :goto_5

    .line 30
    :cond_8
    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zza()I

    move-result v7

    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhs;->zzb()Ljava/lang/String;

    move-result-object v6

    .line 33
    invoke-virtual {v6}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 30
    invoke-static {v7, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzd(ILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;

    move-result-object v6

    .line 31
    invoke-virtual {v2, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zzd(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    :goto_5
    add-int/lit8 v5, v5, 0x1

    goto :goto_3

    .line 34
    :cond_9
    invoke-virtual {p2}, Landroid/os/Bundle;->hasFileDescriptors()Z

    move-result v3

    if-eqz v3, :cond_a

    .line 35
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzl(Landroid/os/Bundle;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 36
    :cond_a
    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p2

    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zza(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzb()F

    move-result p2

    .line 37
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzb(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzc()I

    move-result p2

    .line 38
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzc(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzd()F

    move-result p2

    .line 39
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzr(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p2

    .line 40
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzd(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzf()I

    move-result p2

    .line 41
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zze(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzg()I

    move-result p2

    .line 42
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzf(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzj()Z

    move-result p2

    .line 43
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzh(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzh()I

    move-result p2

    .line 44
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzi(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzk()I

    move-result p2

    .line 45
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzj(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzl()I

    move-result p2

    .line 46
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzk(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 47
    invoke-virtual {p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzq(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzq()I

    move-result p2

    .line 48
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 49
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcv;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;

    move-result-object p2

    .line 50
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzn(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 51
    invoke-virtual {p1, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzo(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzv()Z

    move-result p2

    .line 52
    invoke-virtual {p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzt(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzw()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

    move-result-object p2

    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p2

    .line 53
    invoke-virtual {p2}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result p2

    if-eqz p2, :cond_b

    goto :goto_6

    .line 62
    :cond_b
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhy;->zzw()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;

    move-result-object p0

    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;

    .line 54
    invoke-direct {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;-><init>()V

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzja;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    .line 55
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v2

    if-gtz v2, :cond_c

    .line 58
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmh;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p0

    .line 59
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;

    .line 60
    invoke-direct {v0, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;-><init>(Ljava/util/List;)V

    .line 61
    :goto_6
    invoke-virtual {p1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzu(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 62
    invoke-virtual {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzv()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;

    move-result-object p0

    return-object p0

    .line 55
    :cond_c
    invoke-interface {p0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    .line 56
    invoke-static {p0}, Landroid/support/v4/media/session/MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;->m(Ljava/lang/Object;)V

    .line 57
    throw v0
.end method
