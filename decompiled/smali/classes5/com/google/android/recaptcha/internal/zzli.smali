.class public final Lcom/google/android/recaptcha/internal/zzli;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Landroid/app/Application;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzhh;

.field private zzc:Lcom/google/android/recaptcha/internal/zzaiw;


# direct methods
.method public constructor <init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhh;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzli;->zza:Landroid/app/Application;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzli;->zzb:Lcom/google/android/recaptcha/internal/zzhh;

    return-void
.end method

.method private static final zzb()Ljava/lang/String;
    .locals 1

    .line 1
    :try_start_0
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v0

    .line 2
    invoke-virtual {v0}, Ljava/util/Locale;->getISO3Country()Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catch Ljava/util/MissingResourceException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    const-string v0, ""

    return-object v0
.end method

.method private static final zzc()Ljava/lang/String;
    .locals 1

    .line 1
    :try_start_0
    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v0

    .line 2
    invoke-virtual {v0}, Ljava/util/Locale;->getISO3Language()Ljava/lang/String;

    move-result-object v0
    :try_end_0
    .catch Ljava/util/MissingResourceException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    const-string v0, ""

    return-object v0
.end method


# virtual methods
.method public final zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajr;
    .locals 10

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzli;->zzb:Lcom/google/android/recaptcha/internal/zzhh;

    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzhh;->zzb()Ljava/util/Set;

    move-result-object v0

    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzli;->zzc:Lcom/google/android/recaptcha/internal/zzaiw;

    if-nez v1, :cond_5

    .line 2
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaiw;->zza()Lcom/google/android/recaptcha/internal/zzaiu;

    move-result-object v1

    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    .line 3
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzaiu;->zza(I)Lcom/google/android/recaptcha/internal/zzaiu;

    .line 9
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzli;->zza:Landroid/app/Application;

    .line 3
    const-string v4, "com.google.android.gms.version"

    const/16 v5, 0x21

    const-string/jumbo v6, "unknown"

    const/4 v7, -0x1

    if-lt v2, v5, :cond_1

    .line 4
    :try_start_0
    invoke-virtual {v3}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v2

    .line 5
    invoke-virtual {v3}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v3

    const-wide/16 v8, 0x80

    .line 6
    invoke-static {v8, v9}, Landroid/content/pm/PackageManager$ApplicationInfoFlags;->of(J)Landroid/content/pm/PackageManager$ApplicationInfoFlags;

    move-result-object v8

    .line 7
    invoke-virtual {v2, v3, v8}, Landroid/content/pm/PackageManager;->getApplicationInfo(Ljava/lang/String;Landroid/content/pm/PackageManager$ApplicationInfoFlags;)Landroid/content/pm/ApplicationInfo;

    move-result-object v2

    iget-object v2, v2, Landroid/content/pm/ApplicationInfo;->metaData:Landroid/os/Bundle;

    .line 8
    invoke-virtual {v2, v4, v7}, Landroid/os/BaseBundle;->getInt(Ljava/lang/String;I)I

    move-result v2

    if-ne v2, v7, :cond_0

    :catch_0
    :goto_0
    move-object v2, v6

    goto :goto_1

    .line 9
    :cond_0
    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    goto :goto_1

    .line 10
    :cond_1
    invoke-virtual {v3}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v2

    .line 11
    invoke-virtual {v3}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v3

    const/16 v8, 0x80

    invoke-virtual {v2, v3, v8}, Landroid/content/pm/PackageManager;->getApplicationInfo(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;

    move-result-object v2

    iget-object v2, v2, Landroid/content/pm/ApplicationInfo;->metaData:Landroid/os/Bundle;

    .line 12
    invoke-virtual {v2, v4, v7}, Landroid/os/BaseBundle;->getInt(Ljava/lang/String;I)I

    move-result v2

    if-ne v2, v7, :cond_2

    goto :goto_0

    .line 13
    :cond_2
    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2
    :try_end_0
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 14
    :goto_1
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzaiu;->zzb(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaiu;

    const-string v2, "18.9.2"

    .line 15
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzaiu;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaiu;

    sget-object v2, Landroid/os/Build;->MODEL:Ljava/lang/String;

    .line 16
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzaiu;->zzd(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaiu;

    sget-object v2, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    .line 17
    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzaiu;->zze(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaiu;

    :try_start_1
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    if-lt v2, v5, :cond_3

    iget-object v2, p0, Lcom/google/android/recaptcha/internal/zzli;->zza:Landroid/app/Application;

    .line 18
    invoke-virtual {v2}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v3

    .line 19
    invoke-virtual {v2}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v2

    const-wide/16 v4, 0x0

    .line 20
    invoke-static {v4, v5}, Landroid/content/pm/PackageManager$PackageInfoFlags;->of(J)Landroid/content/pm/PackageManager$PackageInfoFlags;

    move-result-object v4

    .line 21
    invoke-virtual {v3, v2, v4}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;Landroid/content/pm/PackageManager$PackageInfoFlags;)Landroid/content/pm/PackageInfo;

    move-result-object v2

    .line 22
    invoke-virtual {v2}, Landroid/content/pm/PackageInfo;->getLongVersionCode()J

    move-result-wide v2

    .line 23
    invoke-static {v2, v3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v6
    :try_end_1
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_2

    .line 27
    :cond_3
    iget-object v3, p0, Lcom/google/android/recaptcha/internal/zzli;->zza:Landroid/app/Application;

    const/4 v4, 0x0

    const/16 v5, 0x1c

    if-lt v2, v5, :cond_4

    .line 24
    :try_start_2
    invoke-virtual {v3}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v2

    .line 25
    invoke-virtual {v3}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3, v4}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v2

    .line 26
    invoke-virtual {v2}, Landroid/content/pm/PackageInfo;->getLongVersionCode()J

    move-result-wide v2

    .line 27
    invoke-static {v2, v3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v6

    goto :goto_2

    .line 28
    :cond_4
    invoke-virtual {v3}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v2

    .line 29
    invoke-virtual {v3}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3, v4}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v2

    iget v2, v2, Landroid/content/pm/PackageInfo;->versionCode:I

    .line 30
    invoke-static {v2}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v6
    :try_end_2
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_2 .. :try_end_2} :catch_1

    .line 31
    :catch_1
    :goto_2
    invoke-virtual {v1, v6}, Lcom/google/android/recaptcha/internal/zzaiu;->zzf(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzaiu;

    .line 32
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzaiw;

    :cond_5
    iput-object v1, p0, Lcom/google/android/recaptcha/internal/zzli;->zzc:Lcom/google/android/recaptcha/internal/zzaiw;

    .line 33
    invoke-virtual {v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzG()Lcom/google/android/recaptcha/internal/zzafq;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzaiu;

    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzaiu;->zzg(Ljava/lang/Iterable;)Lcom/google/android/recaptcha/internal/zzaiu;

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    .line 34
    check-cast p0, Lcom/google/android/recaptcha/internal/zzaiw;

    .line 35
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzajr;->zza()Lcom/google/android/recaptcha/internal/zzajq;

    move-result-object v0

    .line 36
    invoke-virtual {v0, p1}, Lcom/google/android/recaptcha/internal/zzajq;->zza(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajq;

    .line 37
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzajq;->zzb(Lcom/google/android/recaptcha/internal/zzaiw;)Lcom/google/android/recaptcha/internal/zzajq;

    .line 38
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzli;->zzc()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzajq;->zzc(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajq;

    .line 39
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzli;->zzb()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzajq;->zzd(Ljava/lang/String;)Lcom/google/android/recaptcha/internal/zzajq;

    .line 40
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzafq;->zzp()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzajr;

    return-object p0
.end method
