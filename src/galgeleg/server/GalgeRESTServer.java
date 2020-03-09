package galgeleg.server;

import brugerautorisation.data.SpilInfo;

import galgeleg.logik.Galgelogik;

import io.javalin.Javalin;
import io.javalin.http.Context;

import static io.javalin.apibuilder.ApiBuilder.before;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import java.util.TreeMap;

public class GalgeRESTServer {

    private static TreeMap<Integer, GalgeI> aktiveSpil = new TreeMap<>();
    private static Integer nextID = 1;

    //TODO: ordentlig exception handling
    public static void main(String[] args) throws Exception {

        Javalin app = Javalin.create(config ->
                config.addStaticFiles("/public"))
                .start(4321);

        app.routes(() -> {
            before(ctx -> System.out.println(
                    "Server fik " + ctx.method()
                    + " på " + ctx.url()
                    + " med query " + ctx.queryParamMap()));
            get("/spil", ctx -> getAlleSpil(ctx));
            get("/spil/:id", ctx -> getSpil(ctx));
            post("/spil", ctx -> nytSpil(ctx));
            put("/spil/:id", ctx -> lavGæt(ctx));
        });
    }

    private static void getAlleSpil(Context ctx) throws Exception {
        // Galgelogikkerne konverteres først til SpilInfo-objekter
        TreeMap<Integer, SpilInfo> aktiveSpilTilstande = new TreeMap<>();

        for (Integer key : aktiveSpil.keySet()) {
            aktiveSpilTilstande.put(key, getSpilTilstandSomObjekt(key));
        }

        ctx.json(aktiveSpilTilstande);
    }

    private static void getSpil(Context ctx) throws Exception {
        // Få ID'et fra URL'en og hent det relevante spil
        Integer ID = Integer.parseInt(ctx.pathParam("id"));

        SpilInfo spilInfo = getSpilTilstandSomObjekt(ID);

        ctx.json(spilInfo);
    }

    private static void nytSpil(Context ctx) throws Exception {
        GalgeI galgelogik = new Galgelogik();
        aktiveSpil.put(nextID, galgelogik);

        // Det kreerede spils ID returneres
        ctx.result(nextID.toString());

        nextID++;
    }

    private static void lavGæt(Context ctx) throws Exception {
        Integer ID = Integer.parseInt(ctx.pathParam("id"));
        String bogstav = ctx.queryParam("bogstav");
        System.out.println(ID);
        System.out.println(bogstav);

        aktiveSpil.get(ID).gætBogstav(bogstav);
    }

    private static SpilInfo getSpilTilstandSomObjekt(Integer ID) throws Exception {
        GalgeI galgelogik = aktiveSpil.get(ID);

        SpilInfo spilInfo = new SpilInfo(
                ID,
                galgelogik.getOrdet(),
                galgelogik.getSynligtOrd(),
                galgelogik.getAntalForkerteBogstaver(),
                galgelogik.erSidsteBogstavKorrekt(),
                galgelogik.erSpilletVundet(),
                galgelogik.erSpilletTabt()
        );
        return spilInfo;
    }
}
