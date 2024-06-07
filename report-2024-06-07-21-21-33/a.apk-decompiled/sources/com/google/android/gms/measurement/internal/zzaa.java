package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@21.2.0 */
/* loaded from: classes.dex */
public final class zzaa extends zzkh {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(zzkt zzktVar) {
        super(zzktVar);
    }

    private final zzu zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzu) this.zzc.get(num);
        }
        zzu zzuVar = new zzu(this, this.zza, null);
        this.zzc.put(num, zzuVar);
        return zzuVar;
    }

    private final boolean zzf(int i, int i2) {
        zzu zzuVar = (zzu) this.zzc.get(Integer.valueOf(i));
        if (zzuVar == null) {
            return false;
        }
        return zzu.zzb(zzuVar).get(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(16:(6:19|20|21|22|23|(21:(7:25|26|27|28|(1:30)(3:518|(1:520)(1:522)|521)|31|(1:34)(1:33))|(1:36)|37|38|39|40|41|42|(3:44|(1:46)|47)(4:476|(6:477|478|479|480|481|(1:484)(1:483))|(1:486)|487)|48|(1:50)(6:284|(7:286|287|288|289|290|(1:461)|(3:292|(1:294)|295))(1:475)|304|(10:307|(3:311|(4:314|(5:316|317|(1:319)(1:323)|320|321)(1:324)|322|312)|325)|326|(3:330|(4:333|(3:338|339|340)|341|331)|344)|345|(3:347|(6:350|(2:352|(3:354|355|356))(1:359)|357|358|356|348)|360)|361|(3:370|(8:373|(1:375)|376|(1:378)|379|(3:381|382|383)(1:385)|384|371)|386)|387|305)|393|394)|51|(3:182|(4:185|(10:187|188|(1:190)(1:281)|191|(10:193|194|195|196|197|198|199|200|(4:202|(11:203|204|205|206|207|208|209|(3:211|212|213)(1:256)|214|215|(1:218)(1:217))|(1:220)|221)(2:262|263)|222)(1:280)|223|(4:226|(3:244|245|246)(4:228|229|(2:230|(2:232|(1:234)(2:235|236))(1:243))|(3:238|239|240)(1:242))|241|224)|247|248|249)(1:282)|250|183)|283)|53|54|(3:81|(6:84|(7:86|87|88|89|90|(3:(9:92|93|94|95|96|(1:98)(1:157)|99|100|(1:103)(1:102))|(1:105)|106)(2:164|165)|107)(1:180)|108|(2:109|(2:111|(3:147|148|149)(8:113|(2:114|(4:116|(3:118|(1:120)(1:143)|121)(1:144)|122|(1:1)(2:126|(1:128)(2:129|130)))(2:145|146))|137|(1:139)(1:141)|140|132|133|134))(0))|150|82)|181)|56|57|(9:60|61|62|63|64|65|(2:67|68)(1:70)|69|58)|78|79)(1:526))|41|42|(0)(0)|48|(0)(0)|51|(0)|53|54|(0)|56|57|(1:58)|78|79) */
    /* JADX WARN: Can't wrap try/catch for region: R(26:1|(2:2|(2:4|(2:6|7)(1:542))(2:543|544))|8|(3:10|11|12)|16|(6:19|20|21|22|23|(21:(7:25|26|27|28|(1:30)(3:518|(1:520)(1:522)|521)|31|(1:34)(1:33))|(1:36)|37|38|39|40|41|42|(3:44|(1:46)|47)(4:476|(6:477|478|479|480|481|(1:484)(1:483))|(1:486)|487)|48|(1:50)(6:284|(7:286|287|288|289|290|(1:461)|(3:292|(1:294)|295))(1:475)|304|(10:307|(3:311|(4:314|(5:316|317|(1:319)(1:323)|320|321)(1:324)|322|312)|325)|326|(3:330|(4:333|(3:338|339|340)|341|331)|344)|345|(3:347|(6:350|(2:352|(3:354|355|356))(1:359)|357|358|356|348)|360)|361|(3:370|(8:373|(1:375)|376|(1:378)|379|(3:381|382|383)(1:385)|384|371)|386)|387|305)|393|394)|51|(3:182|(4:185|(10:187|188|(1:190)(1:281)|191|(10:193|194|195|196|197|198|199|200|(4:202|(11:203|204|205|206|207|208|209|(3:211|212|213)(1:256)|214|215|(1:218)(1:217))|(1:220)|221)(2:262|263)|222)(1:280)|223|(4:226|(3:244|245|246)(4:228|229|(2:230|(2:232|(1:234)(2:235|236))(1:243))|(3:238|239|240)(1:242))|241|224)|247|248|249)(1:282)|250|183)|283)|53|54|(3:81|(6:84|(7:86|87|88|89|90|(3:(9:92|93|94|95|96|(1:98)(1:157)|99|100|(1:103)(1:102))|(1:105)|106)(2:164|165)|107)(1:180)|108|(2:109|(2:111|(3:147|148|149)(8:113|(2:114|(4:116|(3:118|(1:120)(1:143)|121)(1:144)|122|(1:1)(2:126|(1:128)(2:129|130)))(2:145|146))|137|(1:139)(1:141)|140|132|133|134))(0))|150|82)|181)|56|57|(9:60|61|62|63|64|65|(2:67|68)(1:70)|69|58)|78|79)(1:526))|541|38|39|40|41|42|(0)(0)|48|(0)(0)|51|(0)|53|54|(0)|56|57|(1:58)|78|79|(5:(0)|(0)|(0)|(0)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0a99, code lost:
    
        if (r7 != false) goto L496;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x096d, code lost:
    
        if (r13 == null) goto L375;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0947, code lost:
    
        r13.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0945, code lost:
    
        if (r13 != null) goto L363;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x07c6, code lost:
    
        if (r5 != null) goto L292;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x0796, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x0794, code lost:
    
        if (r5 != null) goto L292;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x02cc, code lost:
    
        if (r5 != null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:299:0x02ce, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:300:0x02fe, code lost:
    
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r13);
        r1 = new androidx.collection.ArrayMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x030d, code lost:
    
        if (r13.isEmpty() == false) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:302:0x030f, code lost:
    
        r21 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x045c, code lost:
    
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x0313, code lost:
    
        r3 = r13.keySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x031f, code lost:
    
        if (r3.hasNext() == false) goto L546;
     */
    /* JADX WARN: Code restructure failed: missing block: B:398:0x0321, code lost:
    
        r4 = ((java.lang.Integer) r3.next()).intValue();
        r5 = java.lang.Integer.valueOf(r4);
        r6 = (com.google.android.gms.internal.measurement.zzgi) r13.get(r5);
        r7 = (java.util.List) r0.get(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x033b, code lost:
    
        if (r7 == null) goto L549;
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x0341, code lost:
    
        if (r7.isEmpty() == false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x0345, code lost:
    
        r17 = r0;
        r0 = r64.zzf.zzu().zzq(r6.zzk(), r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x0359, code lost:
    
        if (r0.isEmpty() != false) goto L545;
     */
    /* JADX WARN: Code restructure failed: missing block: B:405:0x0447, code lost:
    
        r0 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x035b, code lost:
    
        r5 = (com.google.android.gms.internal.measurement.zzgh) r6.zzby();
        r5.zzf();
        r5.zzb(r0);
        r20 = r3;
        r0 = r64.zzf.zzu().zzq(r6.zzn(), r7);
        r5.zzh();
        r5.zzd(r0);
        com.google.android.gms.internal.measurement.zzoc.zzc();
        r21 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:410:0x038f, code lost:
    
        if (r64.zzt.zzf().zzs(null, com.google.android.gms.measurement.internal.zzdu.zzas) == false) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x0391, code lost:
    
        r0 = new java.util.ArrayList();
        r3 = r6.zzj().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:413:0x03a2, code lost:
    
        if (r3.hasNext() == false) goto L553;
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x03a4, code lost:
    
        r8 = (com.google.android.gms.internal.measurement.zzfr) r3.next();
        r23 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:415:0x03ba, code lost:
    
        if (r7.contains(java.lang.Integer.valueOf(r8.zza())) != false) goto L555;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x03bc, code lost:
    
        r0.add(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:418:0x03bf, code lost:
    
        r3 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x03c3, code lost:
    
        r5.zze();
        r5.zza(r0);
        r0 = new java.util.ArrayList();
        r3 = r6.zzm().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:423:0x03da, code lost:
    
        if (r3.hasNext() == false) goto L556;
     */
    /* JADX WARN: Code restructure failed: missing block: B:424:0x03dc, code lost:
    
        r6 = (com.google.android.gms.internal.measurement.zzgk) r3.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:425:0x03ee, code lost:
    
        if (r7.contains(java.lang.Integer.valueOf(r6.zzb())) != false) goto L559;
     */
    /* JADX WARN: Code restructure failed: missing block: B:427:0x03f0, code lost:
    
        r0.add(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:432:0x03f4, code lost:
    
        r5.zzg();
        r5.zzc(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x0439, code lost:
    
        r1.put(java.lang.Integer.valueOf(r4), (com.google.android.gms.internal.measurement.zzgi) r5.zzaC());
     */
    /* JADX WARN: Code restructure failed: missing block: B:434:0x0454, code lost:
    
        r0 = r17;
        r3 = r20;
        r8 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:436:0x03fb, code lost:
    
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:438:0x0400, code lost:
    
        if (r0 >= r6.zza()) goto L561;
     */
    /* JADX WARN: Code restructure failed: missing block: B:440:0x0412, code lost:
    
        if (r7.contains(java.lang.Integer.valueOf(r6.zze(r0).zza())) == false) goto L563;
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x0414, code lost:
    
        r5.zzi(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x0417, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:446:0x041a, code lost:
    
        r0 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:448:0x041f, code lost:
    
        if (r0 >= r6.zzc()) goto L564;
     */
    /* JADX WARN: Code restructure failed: missing block: B:450:0x0431, code lost:
    
        if (r7.contains(java.lang.Integer.valueOf(r6.zzi(r0).zzb())) == false) goto L566;
     */
    /* JADX WARN: Code restructure failed: missing block: B:451:0x0433, code lost:
    
        r5.zzj(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:453:0x0436, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:457:0x044b, code lost:
    
        r17 = r0;
        r20 = r3;
        r21 = r8;
        r1.put(r5, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:462:0x02d6, code lost:
    
        if (r5 != null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:465:0x02fb, code lost:
    
        if (r5 == null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:506:0x0228, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:507:0x0229, code lost:
    
        r18 = "audience_id";
     */
    /* JADX WARN: Code restructure failed: missing block: B:514:0x0232, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:515:0x0233, code lost:
    
        r18 = "audience_id";
        r19 = "data";
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:516:0x022e, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:517:0x022f, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:527:0x0151, code lost:
    
        if (r5 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:528:0x0153, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:531:0x0175, code lost:
    
        if (r5 == null) goto L58;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0617  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01b4 A[Catch: SQLiteException -> 0x0228, all -> 0x0b5a, TRY_LEAVE, TryCatch #12 {SQLiteException -> 0x0228, blocks: (B:42:0x01ae, B:44:0x01b4, B:476:0x01c4, B:477:0x01c9, B:479:0x01d3, B:480:0x01e3, B:496:0x01f2), top: B:41:0x01ae }] */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x01c4 A[Catch: SQLiteException -> 0x0228, all -> 0x0b5a, TRY_ENTER, TryCatch #12 {SQLiteException -> 0x0228, blocks: (B:42:0x01ae, B:44:0x01b4, B:476:0x01c4, B:477:0x01c9, B:479:0x01d3, B:480:0x01e3, B:496:0x01f2), top: B:41:0x01ae }] */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0aca  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x087c  */
    /* JADX WARN: Type inference failed for: r4v29, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r5v59, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v60 */
    /* JADX WARN: Type inference failed for: r5v61, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r5v8, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List zza(java.lang.String r65, java.util.List r66, java.util.List r67, java.lang.Long r68, java.lang.Long r69) {
        /*
            Method dump skipped, instructions count: 2914
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaa.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long):java.util.List");
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    protected final boolean zzb() {
        return false;
    }
}
