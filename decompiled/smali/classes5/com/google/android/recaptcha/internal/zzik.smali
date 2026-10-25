.class public final Lcom/google/android/recaptcha/internal/zzik;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final zza:Lcom/google/android/recaptcha/internal/zzik;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzik;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzik;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzik;->zza:Lcom/google/android/recaptcha/internal/zzik;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static synthetic zza(Lcom/google/android/recaptcha/internal/zzik;Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzii;ILjava/lang/Object;)Lcom/google/android/recaptcha/internal/zzko;
    .locals 19

    move-object/from16 v1, p1

    .line 1
    sget-object v6, Lcom/google/android/recaptcha/internal/zzih;->zza:Lcom/google/android/recaptcha/internal/zzih;

    new-instance v7, Lcom/google/android/recaptcha/internal/zzhu;

    .line 2
    invoke-direct {v7}, Lcom/google/android/recaptcha/internal/zzhu;-><init>()V

    new-instance v0, Lcom/google/android/recaptcha/internal/zznd;

    const/4 v2, 0x1

    .line 3
    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zznd;-><init>(Z)V

    .line 4
    invoke-static {v1}, Lcom/google/android/recaptcha/internal/zzik;->zzb(Landroid/app/Application;)Lorg/chromium/net/CronetEngine;

    move-result-object v3

    .line 5
    const-string v4, "rC"

    const/4 v5, 0x0

    invoke-static {v4, v5}, Lcom/google/android/recaptcha/internal/zzhv;->zza(Ljava/lang/String;Ljava/lang/Integer;)Ljava/util/concurrent/ThreadFactory;

    move-result-object v4

    .line 6
    invoke-static {v4}, Ljava/util/concurrent/Executors;->newCachedThreadPool(Ljava/util/concurrent/ThreadFactory;)Ljava/util/concurrent/ExecutorService;

    move-result-object v4

    new-instance v8, Lcom/google/android/recaptcha/internal/zzna;

    invoke-direct {v8, v0, v3, v4}, Lcom/google/android/recaptcha/internal/zzna;-><init>(Lcom/google/android/recaptcha/internal/zznd;Lorg/chromium/net/CronetEngine;Ljava/util/concurrent/ExecutorService;)V

    new-instance v13, Lcom/google/android/recaptcha/internal/zzms;

    .line 7
    invoke-direct {v13, v8}, Lcom/google/android/recaptcha/internal/zzms;-><init>(Lcom/google/android/recaptcha/internal/zzmx;)V

    new-instance v14, Lcom/google/android/recaptcha/internal/zzmi;

    invoke-direct {v14, v8}, Lcom/google/android/recaptcha/internal/zzmi;-><init>(Lcom/google/android/recaptcha/internal/zzmx;)V

    new-instance v12, Lcom/google/android/recaptcha/internal/zzmo;

    invoke-direct {v12, v8}, Lcom/google/android/recaptcha/internal/zzmo;-><init>(Lcom/google/android/recaptcha/internal/zzmx;)V

    .line 8
    new-instance v11, Lcom/google/android/recaptcha/internal/zzhq;

    invoke-interface {v6}, Lcom/google/android/recaptcha/internal/zzii;->zzb()Ljava/lang/String;

    move-result-object v0

    .line 9
    invoke-direct {v11, v0}, Lcom/google/android/recaptcha/internal/zzhq;-><init>(Ljava/lang/String;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzlz;

    new-instance v3, Lcom/google/android/recaptcha/internal/zzmm;

    invoke-direct {v3, v11, v8}, Lcom/google/android/recaptcha/internal/zzmm;-><init>(Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzmx;)V

    .line 10
    invoke-direct {v0, v3}, Lcom/google/android/recaptcha/internal/zzlz;-><init>(Lcom/google/android/recaptcha/internal/zzmm;)V

    new-instance v3, Lcom/google/android/recaptcha/internal/zzmq;

    invoke-interface {v6}, Lcom/google/android/recaptcha/internal/zzii;->zza()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v3, v4, v8}, Lcom/google/android/recaptcha/internal/zzmq;-><init>(Ljava/lang/String;Lcom/google/android/recaptcha/internal/zzmx;)V

    new-instance v4, Lcom/google/android/recaptcha/internal/zzlq;

    const/4 v9, 0x2

    .line 11
    invoke-direct {v4, v1, v5, v9, v5}, Lcom/google/android/recaptcha/internal/zzlq;-><init>(Landroid/content/Context;Lcom/google/android/recaptcha/internal/zzln;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    new-instance v9, Lcom/google/android/recaptcha/internal/zzlx;

    .line 12
    invoke-direct {v9, v1, v0, v7, v4}, Lcom/google/android/recaptcha/internal/zzlx;-><init>(Landroid/content/Context;Lcom/google/android/recaptcha/internal/zzlz;Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/recaptcha/internal/zzlq;)V

    new-instance v15, Lcom/google/android/recaptcha/internal/zzhh;

    .line 13
    invoke-direct {v15, v1}, Lcom/google/android/recaptcha/internal/zzhh;-><init>(Landroid/content/Context;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzli;

    .line 14
    invoke-direct {v0, v1, v15}, Lcom/google/android/recaptcha/internal/zzli;-><init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhh;)V

    new-instance v4, Lcom/google/android/recaptcha/internal/zzhd;

    .line 15
    invoke-direct {v4, v5, v2, v5}, Lcom/google/android/recaptcha/internal/zzhd;-><init>(Ljava/util/List;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 16
    new-instance v10, Lcom/google/android/recaptcha/internal/zzlj;

    invoke-direct {v10, v9, v0, v4}, Lcom/google/android/recaptcha/internal/zzlj;-><init>(Lcom/google/android/recaptcha/internal/zzlr;Lcom/google/android/recaptcha/internal/zzli;Lcom/google/android/recaptcha/internal/zzhd;)V

    new-instance v16, Lcom/google/android/recaptcha/internal/zzht;

    invoke-direct/range {v16 .. v16}, Lcom/google/android/recaptcha/internal/zzht;-><init>()V

    new-instance v0, Lcom/google/android/recaptcha/internal/zznn;

    new-instance v2, Lcom/google/android/recaptcha/internal/zzno;

    .line 17
    invoke-direct {v2, v1}, Lcom/google/android/recaptcha/internal/zzno;-><init>(Landroid/content/Context;)V

    invoke-direct {v0, v2}, Lcom/google/android/recaptcha/internal/zznn;-><init>(Lcom/google/android/recaptcha/internal/zzno;)V

    new-instance v2, Lcom/google/android/recaptcha/internal/zzns;

    new-instance v4, Lcom/google/android/recaptcha/internal/zzop;

    invoke-direct {v4, v1}, Lcom/google/android/recaptcha/internal/zzop;-><init>(Landroid/content/Context;)V

    .line 18
    invoke-direct {v2, v1, v4}, Lcom/google/android/recaptcha/internal/zzns;-><init>(Landroid/content/Context;Lcom/google/android/recaptcha/internal/zzop;)V

    new-instance v9, Lcom/google/android/recaptcha/internal/zzgy;

    .line 19
    invoke-direct {v9, v3, v2, v7}, Lcom/google/android/recaptcha/internal/zzgy;-><init>(Lcom/google/android/recaptcha/internal/zzmq;Lcom/google/android/recaptcha/internal/zzne;Lcom/google/android/recaptcha/internal/zzhu;)V

    .line 20
    invoke-static {v1}, Lcom/google/android/play/core/integrity/IntegrityManagerFactory;->createStandard(Landroid/content/Context;)Lcom/google/android/play/core/integrity/StandardIntegrityManager;

    move-result-object v17

    .line 21
    new-instance v3, Lcom/google/android/recaptcha/internal/zzgp;

    new-instance v4, Lcom/google/android/recaptcha/internal/zzg;

    .line 22
    invoke-direct {v4}, Lcom/google/android/recaptcha/internal/zzg;-><init>()V

    .line 23
    invoke-direct {v3, v4}, Lcom/google/android/recaptcha/internal/zzgp;-><init>(Lcom/google/android/recaptcha/internal/zzd;)V

    new-instance v4, Lcom/google/android/recaptcha/internal/zznk;

    const-string v5, "recck"

    .line 24
    invoke-direct {v4, v5}, Lcom/google/android/recaptcha/internal/zznk;-><init>(Ljava/lang/String;)V

    move-object v5, v3

    new-instance v3, Lcom/google/android/recaptcha/internal/zzhr;

    move-object/from16 p0, v5

    .line 25
    invoke-static {}, Lcom/google/android/gms/common/GoogleApiAvailabilityLight;->getInstance()Lcom/google/android/gms/common/GoogleApiAvailabilityLight;

    move-result-object v5

    .line 26
    invoke-direct {v3, v1, v5}, Lcom/google/android/recaptcha/internal/zzhr;-><init>(Landroid/content/Context;Lcom/google/android/gms/common/GoogleApiAvailabilityLight;)V

    new-instance v5, Lcom/google/android/recaptcha/internal/zzon;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzni;

    .line 27
    invoke-direct {v1, v2, v4}, Lcom/google/android/recaptcha/internal/zzni;-><init>(Lcom/google/android/recaptcha/internal/zzne;Lcom/google/android/recaptcha/internal/zznf;)V

    move-object/from16 p2, v3

    new-instance v3, Lcom/google/android/recaptcha/internal/zzng;

    .line 28
    invoke-direct {v3, v0, v4}, Lcom/google/android/recaptcha/internal/zzng;-><init>(Lcom/google/android/recaptcha/internal/zznn;Lcom/google/android/recaptcha/internal/zznf;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zznm;

    .line 29
    invoke-direct {v0, v2, v4}, Lcom/google/android/recaptcha/internal/zznm;-><init>(Lcom/google/android/recaptcha/internal/zzne;Lcom/google/android/recaptcha/internal/zznf;)V

    .line 30
    invoke-direct {v5, v1, v3, v0}, Lcom/google/android/recaptcha/internal/zzon;-><init>(Lcom/google/android/recaptcha/internal/zzni;Lcom/google/android/recaptcha/internal/zzng;Lcom/google/android/recaptcha/internal/zznm;)V

    new-instance v0, Lcom/google/android/recaptcha/internal/zzif;

    move-object v3, v5

    const/4 v5, 0x1

    move-object/from16 v2, p0

    move-object/from16 v1, p1

    move-object v4, v3

    move-object/from16 v3, p2

    .line 32
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzif;-><init>(Landroid/content/Context;Lcom/google/android/recaptcha/internal/zzgp;Lcom/google/android/recaptcha/internal/zzhr;Lcom/google/android/recaptcha/internal/zzon;Z)V

    move-object/from16 v18, v0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzlh;

    move-object v5, v9

    .line 33
    new-instance v9, Lcom/google/android/recaptcha/internal/zzdv;

    move-object/from16 p0, v0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzmk;

    invoke-direct {v0, v11, v8}, Lcom/google/android/recaptcha/internal/zzmk;-><init>(Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzmx;)V

    .line 35
    invoke-direct {v9, v0, v15, v7}, Lcom/google/android/recaptcha/internal/zzdv;-><init>(Lcom/google/android/recaptcha/internal/zzmu;Lcom/google/android/recaptcha/internal/zzhh;Lcom/google/android/recaptcha/internal/zzhu;)V

    move-object v0, v10

    new-instance v10, Lcom/google/android/recaptcha/internal/zzij;

    invoke-direct {v10, v1, v3}, Lcom/google/android/recaptcha/internal/zzij;-><init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhr;)V

    move-object v8, v6

    move-object v6, v2

    move-object v2, v8

    move-object v8, v7

    move-object v7, v4

    move-object v4, v8

    move-object v8, v3

    move-object v3, v5

    move-object/from16 v5, v17

    move-object/from16 v17, v0

    move-object/from16 v0, p0

    invoke-direct/range {v0 .. v12}, Lcom/google/android/recaptcha/internal/zzlh;-><init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzii;Lcom/google/android/recaptcha/internal/zzgy;Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/play/core/integrity/StandardIntegrityManager;Lcom/google/android/recaptcha/internal/zzgp;Lcom/google/android/recaptcha/internal/zzon;Lcom/google/android/recaptcha/internal/zzhr;Lcom/google/android/recaptcha/internal/zzdv;Lcom/google/android/recaptcha/internal/zzip;Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzmv;)V

    move-object v2, v7

    move-object v7, v3

    move-object v3, v2

    move-object v6, v0

    move-object v2, v11

    new-instance v0, Lcom/google/android/recaptcha/internal/zzlf;

    move-object v1, v14

    move-object v5, v15

    .line 36
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzlf;-><init>(Lcom/google/android/recaptcha/internal/zzmt;Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzon;Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/recaptcha/internal/zzhh;)V

    move-object v9, v0

    move-object v8, v3

    new-instance v0, Lcom/google/android/recaptcha/internal/zzkw;

    move-object v1, v5

    move-object v5, v4

    move-object v4, v1

    move-object/from16 v1, p1

    move-object v3, v13

    .line 37
    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzkw;-><init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhq;Lcom/google/android/recaptcha/internal/zzmw;Lcom/google/android/recaptcha/internal/zzhh;Lcom/google/android/recaptcha/internal/zzhu;)V

    move-object v4, v5

    new-instance v12, Lcom/google/android/recaptcha/internal/zzid;

    const-string v1, ""

    invoke-direct {v12, v1}, Lcom/google/android/recaptcha/internal/zzid;-><init>(Ljava/lang/String;)V

    move-object v10, v0

    new-instance v0, Lcom/google/android/recaptcha/internal/zzko;

    .line 38
    new-instance v4, Lcom/google/android/recaptcha/internal/zzhl;

    invoke-direct {v4}, Lcom/google/android/recaptcha/internal/zzhl;-><init>()V

    move-object/from16 v1, p1

    move-object v13, v2

    move-object v2, v5

    move-object v3, v6

    move-object/from16 v6, v16

    move-object/from16 v5, v17

    move-object/from16 v11, v18

    .line 40
    invoke-direct/range {v0 .. v13}, Lcom/google/android/recaptcha/internal/zzko;-><init>(Landroid/app/Application;Lcom/google/android/recaptcha/internal/zzhu;Lcom/google/android/recaptcha/internal/zzlg;Lcom/google/android/recaptcha/internal/zzhl;Lcom/google/android/recaptcha/internal/zzlj;Lcom/google/android/recaptcha/internal/zzht;Lcom/google/android/recaptcha/internal/zzgy;Lcom/google/android/recaptcha/internal/zzon;Lcom/google/android/recaptcha/internal/zzkx;Lcom/google/android/recaptcha/internal/zzkp;Lcom/google/android/recaptcha/internal/zzif;Lcom/google/android/recaptcha/internal/zzid;Lcom/google/android/recaptcha/internal/zzhq;)V

    return-object v0
.end method

.method private static final zzb(Landroid/app/Application;)Lorg/chromium/net/CronetEngine;
    .locals 1

    .line 1
    :try_start_0
    new-instance v0, Lorg/chromium/net/CronetEngine$Builder;

    invoke-direct {v0, p0}, Lorg/chromium/net/CronetEngine$Builder;-><init>(Landroid/content/Context;)V

    .line 2
    invoke-virtual {v0}, Lorg/chromium/net/CronetEngine$Builder;->build()Lorg/chromium/net/CronetEngine;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/UnsatisfiedLinkError; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IllegalStateException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 3
    :catch_0
    new-instance v0, Lorg/chromium/net/impl/JavaCronetProvider;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaCronetProvider;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0}, Lorg/chromium/net/impl/JavaCronetProvider;->createBuilder()Lorg/chromium/net/CronetEngine$Builder;

    move-result-object p0

    .line 4
    invoke-virtual {p0}, Lorg/chromium/net/CronetEngine$Builder;->build()Lorg/chromium/net/CronetEngine;

    move-result-object p0

    goto :goto_0

    .line 5
    :catch_1
    new-instance v0, Lorg/chromium/net/impl/JavaCronetProvider;

    invoke-direct {v0, p0}, Lorg/chromium/net/impl/JavaCronetProvider;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0}, Lorg/chromium/net/impl/JavaCronetProvider;->createBuilder()Lorg/chromium/net/CronetEngine$Builder;

    move-result-object p0

    .line 6
    invoke-virtual {p0}, Lorg/chromium/net/CronetEngine$Builder;->build()Lorg/chromium/net/CronetEngine;

    move-result-object p0

    :goto_0
    return-object p0
.end method
