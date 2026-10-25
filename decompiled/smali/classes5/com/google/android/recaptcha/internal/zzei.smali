.class public abstract Lcom/google/android/recaptcha/internal/zzei;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static final zza(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhu;Landroid/content/ContentResolver;Lcom/google/android/play/core/integrity/StandardIntegrityManager;Lcom/google/android/recaptcha/internal/zzgp;Lcom/google/android/recaptcha/internal/zzhr;Z)Lcom/google/android/recaptcha/internal/zzeo;
    .locals 2

    .line 1
    invoke-static {}, Lkotlin/collections/CollectionsKt;->createListBuilder()Ljava/util/List;

    move-result-object p2

    new-instance v0, Lcom/google/android/recaptcha/internal/zzez;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzez;-><init>()V

    .line 2
    invoke-interface {p2, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    new-instance v0, Lcom/google/android/recaptcha/internal/zzeh;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzeh;-><init>()V

    .line 3
    invoke-interface {p2, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    new-instance v0, Lcom/google/android/recaptcha/internal/zzea;

    .line 4
    invoke-virtual {p0}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzea;-><init>(Landroid/content/ContentResolver;)V

    invoke-interface {p2, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    new-instance v0, Lcom/google/android/recaptcha/internal/zzee;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzee;-><init>()V

    .line 5
    invoke-interface {p2, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    new-instance v0, Lcom/google/android/recaptcha/internal/zzec;

    invoke-direct {v0, p0, p4, p5}, Lcom/google/android/recaptcha/internal/zzec;-><init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzgp;Lcom/google/android/recaptcha/internal/zzhr;)V

    .line 6
    invoke-interface {p2, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    if-eqz p6, :cond_0

    new-instance p0, Lcom/google/android/recaptcha/internal/zzfd;

    new-instance p4, Lcom/google/android/recaptcha/internal/zzfp;

    const-wide/32 v0, 0x1b77400

    .line 7
    invoke-direct {p4, p1, p3, v0, v1}, Lcom/google/android/recaptcha/internal/zzfp;-><init>(Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/play/core/integrity/StandardIntegrityManager;J)V

    invoke-direct {p0, p1, p3, p5, p4}, Lcom/google/android/recaptcha/internal/zzfd;-><init>(Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/play/core/integrity/StandardIntegrityManager;Lcom/google/android/recaptcha/internal/zzhr;Lcom/google/android/recaptcha/internal/zzfp;)V

    .line 8
    invoke-interface {p2, p0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 1
    :cond_0
    invoke-static {p2}, Lkotlin/collections/CollectionsKt;->build(Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    new-instance p1, Lcom/google/android/recaptcha/internal/zzeo;

    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzeo;-><init>(Ljava/util/List;)V

    return-object p1
.end method
