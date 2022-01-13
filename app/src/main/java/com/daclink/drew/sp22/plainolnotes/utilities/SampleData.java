package com.daclink.drew.sp22.plainolnotes.utilities;

import com.daclink.drew.sp22.plainolnotes.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {
    private static final String SAMPLE_TEXT_1 = "A simple note";
    private static final String SAMPLE_TEXT_2 = "a note with a\nline feed (who calls it that?)";
    private static final String SAMPLE_TEXT_3 = "Lucas ipsum dolor sit amet bibble su duros nassau kobok trioculus utai terentatek greedo yavin. Darth dunhausen moor drach'nam gotal. Sola kobok qui-gonn skywalker aka balosar sulorine sio. Selonian ponda arcona chagrian fel ebe joruus. Chistori x1 lytton bossk wirutid anx owen gordin rune. Tyranus arcona nass arkanian rune pellaeon. Unduli sabé antonio ablajeck cordé mustafar. Nilgaard gamorrean epicanthix kasan paploo kit obi-wan rahm. Ken gossam jabba yoda. Kal allana melodie darth moff. Maris lyn sabé twi'lek evocii rugor cato ryn mirax.\n" +
            "\n" +
            "Oswaft sebulba nute omwati jusik theelin. Cordé chagrian latter sing nilgaard kal. Toydarian raioballo mohc tund yowza sanyassan mandalore. Bel echani typho tython togruta darklighter crynyd trianii theelin. Lahara trianii kitonak kashyyyk conan clawdite maul. Even disra echani cato. Leia lyn wroonian klatooinian artaru skywalker ventress kenobi khai. Bria amanin bertroff bren offee. Togorian chistori taung organa x1 rendar. Zabrak coway b'omarr abrion yuvernian weequay whaladon ansionian rishii.\n" +
            "\n" +
            "Rebo padmé qel-droma fel farlander. Briqualon ithorian melodie dat terrik subterrel oola tano. Falleen antilles cornelius biggs fortuna rhen mas kor-uj. Porkins cornelius er'kit tatooine. Shawda joruus ssi-ruuk jax sulorine mygeeto bespin. Xizor warrick glymphid moore. Luminara ahsoka castell rune jaina chadra-fan arvel bail. Tion dodonna ziro rattatak phlog. Salacious chazrach arkanian luke sesswenna tiin. Isard finis shimrra darpa gordin zorba bail. Xappyh lepi darth moff danni xexto nas fett md-5.\n" +
            "\n";

    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();

        cal.add(Calendar.MILLISECOND, diff);
        return cal.getTime();
    }

    public static List<NoteEntity> getNotes() {
        List<NoteEntity> notes = new ArrayList<>();
//        notes.add(new NoteEntity(1, getDate(0), SAMPLE_TEXT_1));
//        notes.add(new NoteEntity(2, getDate(-1), SAMPLE_TEXT_2));
//        notes.add(new NoteEntity(3, getDate(-2), SAMPLE_TEXT_3));
        notes.add(new NoteEntity( getDate(0), SAMPLE_TEXT_1));
        notes.add(new NoteEntity( getDate(-1), SAMPLE_TEXT_2));
        notes.add(new NoteEntity( getDate(-2), SAMPLE_TEXT_3));

        return notes;

    }
}
