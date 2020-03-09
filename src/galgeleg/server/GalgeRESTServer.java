package galgeleg.server;

import brugerautorisation.data.SpilInfo;

import galgeleg.logik.Galgelogik;
import io.javalin.Javalin;
import io.javalin.http.Context;
import static io.javalin.apibuilder.ApiBuilder.before;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

import java.util.HashMap;


public class GalgeRESTServer {

    // private HashMap<int, SpilInfo> aktiveSpil;

    //TODO: ordentlig exception handling
    public static void main(String[] args) throws Exception {
        GalgeI galgelogik = new Galgelogik();
        galgelogik.nulstil();

        Javalin app = Javalin.create(config ->
                config.addStaticFiles("/public"))
                .start(4321);

        app.routes(() -> {
            before(ctx -> System.out.println(
                    "Server fik " + ctx.method()
                    + " på " + ctx.url()
                    + " med query " + ctx.queryParamMap()));
            get("/spil", ctx -> getSpil(ctx));
            get("/spilinfo", ctx -> getSpilInfo(ctx, galgelogik));
            post("/spilinfo", ctx -> gaet(ctx, galgelogik));
        });
    }

    private static void getSpil(Context ctx) {

    }

    private static void getSpilInfo(Context ctx, GalgeI galgelogik) throws Exception {
        SpilInfo spilInfo = new SpilInfo(
                galgelogik.getOrdet(),
                galgelogik.getSynligtOrd(),
                galgelogik.getAntalForkerteBogstaver(),
                galgelogik.erSidsteBogstavKorrekt(),
                galgelogik.erSpilletVundet(),
                galgelogik.erSpilletTabt()
        );

        ctx.json(spilInfo);
    }

    private static void gaet(Context ctx, GalgeI galgelogik) throws Exception{
        String bogstav = ctx.queryParam("bogstav");
        galgelogik.gætBogstav(bogstav);
    }
}
