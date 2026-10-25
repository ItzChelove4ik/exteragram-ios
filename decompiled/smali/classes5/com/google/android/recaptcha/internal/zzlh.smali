.class public final Lcom/google/android/recaptcha/internal/zzlh;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzlg;


# instance fields
.field private final zza:Landroid/app/Application;

.field private final zzb:Lcom/google/android/play/core/integrity/StandardIntegrityManager;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzgp;

.field private final zzd:Lcom/google/android/recaptcha/internal/zzon;

.field private final zze:Lcom/google/android/recaptcha/internal/zzdv;

.field private final zzf:Lcom/google/android/recaptcha/internal/zzip;

.field private final zzg:Lcom/google/android/recaptcha/internal/zzhq;

.field private final zzh:Lcom/google/android/recaptcha/internal/zzmv;

.field private final zzi:Lcom/google/android/recaptcha/internal/zzhr;

.field private final zzj:Lcom/google/android/recaptcha/internal/zzgy;

.field private final zzk:Lcom/google/android/recaptcha/internal/zzhu;


# direct methods
.method public constructor <init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzii;Lcom/google/android/recaptcha/internal/zzgy;Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/play/core/integrity/StandardIntegrityManager;Lcom/google/android/recaptcha/internal/zzgp;Lcom/google/android/recaptcha/internal/zzon;Lcom/google/android/recaptcha/internal/zzhr;Lcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzip;Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzmv;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzlh;->zza:Landroid/app/Application;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzj:Lcom/google/android/recaptcha/internal/zzgy;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzk:Lcom/google/android/recaptcha/internal/zzhu;

    iput-object p5, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzb:Lcom/google/android/play/core/integrity/StandardIntegrityManager;

    iput-object p6, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzc:Lcom/google/android/recaptcha/internal/zzgp;

    iput-object p7, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    iput-object p8, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzi:Lcom/google/android/recaptcha/internal/zzhr;

    iput-object p9, p0, Lcom/google/android/recaptcha/internal/zzlh;->zze:Lcom/google/android/recaptcha/internal/zzdv;

    iput-object p10, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzf:Lcom/google/android/recaptcha/internal/zzip;

    iput-object p11, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzg:Lcom/google/android/recaptcha/internal/zzhq;

    iput-object p12, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzh:Lcom/google/android/recaptcha/internal/zzmv;

    return-void
.end method

.method private final zzb(Z)Lcom/google/android/recaptcha/internal/zzcx;
    .locals 11

    .line 1
    invoke-static {}, Lkotlin/collections/CollectionsKt;->createListBuilder()Ljava/util/List;

    move-result-object v0

    .line 2
    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzlh;->zzc(Z)Lcom/google/android/recaptcha/internal/zzeo;

    move-result-object p1

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    new-instance v1, Lcom/google/android/recaptcha/internal/zzgo;

    new-instance v4, Lcom/google/android/recaptcha/internal/zzha;

    .line 3
    invoke-direct {v4}, Lcom/google/android/recaptcha/internal/zzha;-><init>()V

    new-instance v6, Lcom/google/android/recaptcha/internal/zzfw;

    invoke-direct {v6}, Lcom/google/android/recaptcha/internal/zzfw;-><init>()V

    iget-object v7, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzc:Lcom/google/android/recaptcha/internal/zzgp;

    iget-object v8, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzi:Lcom/google/android/recaptcha/internal/zzhr;

    iget-object v9, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzh:Lcom/google/android/recaptcha/internal/zzmv;

    iget-object v10, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzg:Lcom/google/android/recaptcha/internal/zzhq;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzj:Lcom/google/android/recaptcha/internal/zzgy;

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzlh;->zza:Landroid/app/Application;

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzk:Lcom/google/android/recaptcha/internal/zzhu;

    .line 4
    invoke-direct/range {v1 .. v10}, Lcom/google/android/recaptcha/internal/zzgo;-><init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/recaptcha/internal/zzgz;Lcom/google/android/recaptcha/internal/zzgy;Lcom/google/android/recaptcha/internal/zzfw;Lcom/google/android/recaptcha/internal/zzgp;Lcom/google/android/recaptcha/internal/zzhr;Lcom/google/android/recaptcha/internal/zzmv;Lcom/google/android/recaptcha/internal/zzhq;)V

    .line 5
    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzf:Lcom/google/android/recaptcha/internal/zzip;

    .line 6
    invoke-interface {p0}, Lcom/google/android/recaptcha/internal/zzip;->zza()Lcom/google/android/recaptcha/internal/zzcl;

    move-result-object p0

    invoke-interface {v0, p0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1
    invoke-static {v0}, Lkotlin/collections/CollectionsKt;->build(Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    new-instance p1, Lcom/google/android/recaptcha/internal/zzcx;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzct;

    .line 7
    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzct;-><init>(Ljava/util/List;)V

    invoke-direct {p1, v0}, Lcom/google/android/recaptcha/internal/zzcx;-><init>(Lcom/google/android/recaptcha/internal/zzct;)V

    return-object p1
.end method

.method private final zzc(Z)Lcom/google/android/recaptcha/internal/zzeo;
    .locals 7

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzlh;->zza:Landroid/app/Application;

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzk:Lcom/google/android/recaptcha/internal/zzhu;

    invoke-virtual {v0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v2

    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzb:Lcom/google/android/play/core/integrity/StandardIntegrityManager;

    iget-object v4, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzc:Lcom/google/android/recaptcha/internal/zzgp;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzi:Lcom/google/android/recaptcha/internal/zzhr;

    move v6, p1

    .line 2
    invoke-static/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzei;->zza(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhu;Landroid/content/ContentResolver;Lcom/google/android/play/core/integrity/StandardIntegrityManager;Lcom/google/android/recaptcha/internal/zzgp;Lcom/google/android/recaptcha/internal/zzhr;Z)Lcom/google/android/recaptcha/internal/zzeo;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public final zza(Z)Lcom/google/android/recaptcha/internal/zzdp;
    .locals 7

    const/4 v0, 0x1

    if-eqz p1, :cond_0

    .line 1
    new-instance v1, Lcom/google/android/recaptcha/internal/zzdo;

    const/4 p1, 0x0

    invoke-direct {p0, p1}, Lcom/google/android/recaptcha/internal/zzlh;->zzb(Z)Lcom/google/android/recaptcha/internal/zzcx;

    move-result-object v2

    new-instance v3, Lcom/google/android/recaptcha/internal/zzct;

    .line 2
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzlh;->zzc(Z)Lcom/google/android/recaptcha/internal/zzeo;

    move-result-object p1

    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->listOf(Ljava/lang/Object;)Ljava/util/List;

    move-result-object p1

    invoke-direct {v3, p1}, Lcom/google/android/recaptcha/internal/zzct;-><init>(Ljava/util/List;)V

    iget-object p1, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzd:Lcom/google/android/recaptcha/internal/zzon;

    iget-object v5, p0, Lcom/google/android/recaptcha/internal/zzlh;->zze:Lcom/google/android/recaptcha/internal/zzdv;

    iget-object v6, p0, Lcom/google/android/recaptcha/internal/zzlh;->zzk:Lcom/google/android/recaptcha/internal/zzhu;

    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzon;->zzb()Lcom/google/android/recaptcha/internal/zzng;

    move-result-object v4

    .line 3
    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzdo;-><init>(Lcom/google/android/recaptcha/internal/zzcx;Lcom/google/android/recaptcha/internal/zzct;Lcom/google/android/recaptcha/internal/zzng;Lcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzhu;)V

    return-object v1

    .line 4
    :cond_0
    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzlh;->zzb(Z)Lcom/google/android/recaptcha/internal/zzcx;

    move-result-object p0

    return-object p0
.end method
