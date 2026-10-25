.class public final Lcom/google/android/recaptcha/internal/zzadf;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzadf;

.field public static final zzb:Lcom/google/android/recaptcha/internal/zzadf;

.field public static final zzc:Lcom/google/android/recaptcha/internal/zzadf;


# instance fields
.field private final zzd:Lcom/google/android/recaptcha/internal/zzade;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzadf;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzadg;

    invoke-direct {v1}, Lcom/google/android/recaptcha/internal/zzadg;-><init>()V

    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;-><init>(Lcom/google/android/recaptcha/internal/zzadn;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzadf;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzadk;

    invoke-direct {v1}, Lcom/google/android/recaptcha/internal/zzadk;-><init>()V

    .line 2
    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;-><init>(Lcom/google/android/recaptcha/internal/zzadn;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzadf;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzadm;

    invoke-direct {v1}, Lcom/google/android/recaptcha/internal/zzadm;-><init>()V

    .line 3
    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;-><init>(Lcom/google/android/recaptcha/internal/zzadn;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzadf;->zza:Lcom/google/android/recaptcha/internal/zzadf;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzadf;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzadl;

    invoke-direct {v1}, Lcom/google/android/recaptcha/internal/zzadl;-><init>()V

    .line 4
    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;-><init>(Lcom/google/android/recaptcha/internal/zzadn;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzadf;->zzb:Lcom/google/android/recaptcha/internal/zzadf;

    new-instance v0, Lcom/google/android/recaptcha/internal/zzadf;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzadh;

    invoke-direct {v1}, Lcom/google/android/recaptcha/internal/zzadh;-><init>()V

    .line 5
    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;-><init>(Lcom/google/android/recaptcha/internal/zzadn;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzadf;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzadj;

    invoke-direct {v1}, Lcom/google/android/recaptcha/internal/zzadj;-><init>()V

    .line 6
    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;-><init>(Lcom/google/android/recaptcha/internal/zzadn;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzadf;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzadi;

    invoke-direct {v1}, Lcom/google/android/recaptcha/internal/zzadi;-><init>()V

    .line 7
    invoke-direct {v0, v1}, Lcom/google/android/recaptcha/internal/zzadf;-><init>(Lcom/google/android/recaptcha/internal/zzadn;)V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzadf;->zzc:Lcom/google/android/recaptcha/internal/zzadf;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzadn;)V
    .locals 3

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    invoke-static {}, Lcom/google/android/recaptcha/internal/zztf;->zza()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_1

    const-string v0, "The Android Project"

    .line 2
    const-string v2, "java.vendor"

    invoke-static {v2}, Ljava/lang/System;->getProperty(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzadb;

    invoke-direct {v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzadb;-><init>(Lcom/google/android/recaptcha/internal/zzadn;[B)V

    :goto_0
    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzadf;->zzd:Lcom/google/android/recaptcha/internal/zzade;

    return-void

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzadc;

    invoke-direct {v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzadc;-><init>(Lcom/google/android/recaptcha/internal/zzadn;[B)V

    goto :goto_0

    :cond_1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzadd;

    invoke-direct {v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzadd;-><init>(Lcom/google/android/recaptcha/internal/zzadn;[B)V

    goto :goto_0
.end method

.method public static varargs zza([Ljava/lang/String;)Ljava/util/List;
    .locals 3

    .line 1
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const/4 v1, 0x0

    :goto_0
    array-length v2, p0

    if-ge v1, v2, :cond_1

    .line 2
    aget-object v2, p0, v1

    .line 3
    invoke-static {v2}, Ljava/security/Security;->getProvider(Ljava/lang/String;)Ljava/security/Provider;

    move-result-object v2

    if-eqz v2, :cond_0

    .line 4
    invoke-interface {v0, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    return-object v0
.end method


# virtual methods
.method public final zzb(Ljava/lang/String;)Ljava/lang/Object;
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzadf;->zzd:Lcom/google/android/recaptcha/internal/zzade;

    invoke-interface {p0, p1}, Lcom/google/android/recaptcha/internal/zzade;->zza(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method
