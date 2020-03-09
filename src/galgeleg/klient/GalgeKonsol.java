package galgeleg.klient;

import galgeleg.server.SpilI;
import galgeleg.server.GalgeI;

import java.rmi.Naming;
import java.util.Scanner;

public class GalgeKonsol {

    boolean erSpilVundet = false;


    public void spil() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("Hej. Galgeleg er sjovt. Indtast brugernavn:");
        String bruger = input.nextLine();

        System.out.println("Indtast kode:");
        String kode = input.nextLine();

        SpilI spil = (SpilI) Naming.lookup("rmi://ec2-18-189-32-7.us-east-2.compute.amazonaws.com:1099/spil");
        spil.startSpil(bruger, kode);

        GalgeI galgeleg = (GalgeI) Naming.lookup("rmi://ec2-18-189-32-7.us-east-2.compute.amazonaws.com:1099/galgeleg");

        // setup
        galgeleg.nulstil();

//        try {
//            spil.hentOrdFraDr();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        String gæt;

        while (!erSpilVundet) {
            System.out.println("Ok, PLACEHOLDER. Gæt ordet eller dø:");
            System.out.println(galgeleg.getSynligtOrd());
            System.out.println("CHEATZ: " + galgeleg.getOrdet());
            System.out.println("Gæt: ");
            System.out.println();

            gæt = input.next();

            while (!gæt.matches("[A-Åa-å]{1}")) {
                System.out.println("Ét bogstav...");
                gæt = input.next();
            }

            galgeleg.gætBogstav(gæt);

            if (galgeleg.erSpilletSlut() == true) {
                erSpilVundet = true;

                System.out.println("Spillet er slut.");

                input.close();
            }
        }

    }

}
