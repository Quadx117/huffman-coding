/* -------------------------------------------------------------------------
   | Auteur(s) :   Eric Perron        IFT2810                              |
   |-----------------------------------------------------------------------|
   | Dernière mise à jour : 18 novembre 2014                               |
   |-----------------------------------------------------------------------|
 */

package question2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Cette classe contient les méthodes principales de notre programme.
 */
public class Programme {

    /**
     * Valeur indiquant la fin du fichier (retourné par la méthode read de InputStreamReader)
     */
    private final int EOF = -1;

    /**
     * Cette méthode lit un fichier sur le disque dur et retourne ce fichier sous
     * forme de chaîne de caractères {@code String}. Le format du fichier lu par
     * cette méthode est une fichier contenant une séquence de '0' et de '1' sans
     * espace ni retour de ligne. Chaque groupe de 8 caractères représente le code
     * binaire d'un caractère.
     * 
     * @param file
     *            le fichier à lire
     * 
     * @return une chaîne de caractères contenant le texte du fichier lu.
     * 
     * @throws IOException
     *             si une erreur d'entrée-sortie se produit. Spécifié par la
     *             méthode @{@code close()} de {@code InputStreamReader}.
     * @throws FileNotFoundException
     *             si le fichier est introuvable. Spécifié par le constructeur
     *             de la classe {@code FileInputStream}
     */
    public String readFile(String file) throws FileNotFoundException, IOException {

        System.out.println("Lecture du fichier en cours ...");

        // StringBuilder pour construire le texte lue du fichier et le retourné à la fin
        // utilisation d'une valeur suffisamment grande pour minimiser les opérations resize()
        StringBuilder text = new StringBuilder(11000);

        // Ouvrir le fichier.
        InputStreamReader entree = new InputStreamReader(new FileInputStream(file));

        // Lire les bits du fichiers par groupe de 8, les convertir en entier puis en char
        // pour ensuite les ajouter à notre chaine de caractère.
        int input;

        while ((input = entree.read()) != EOF) {
            // Puisque notre fichier contient des mots de 8 bits, nous lisons 8 bits un à la fois
            // et on les convertis en entiers, ce qui nous permet ensuite des les convertir en
            // caractères. Cela nous permet d'uniformiser les méthodes de la classe Huffman.
            // La boucle lit 7 caractèrs puisque nous en avons déjà lu 1 dans le while.
            input = parseInt(input);
            for (int j = 0; j < 7; j++) {
                // Convertir les informations binaires lue en décimale
                // (inspiration : http://www.wikihow.com/Convert-from-Binary-to-Decimal méthode 2)
                input = input * 2 + parseInt(entree.read());
            }

            // ajouter le caractère dans la chaîne de caractère
            text.append((char) input);
        }

        // fermé le fichier
        entree.close();

        System.out.println("Opération terminé.");

        return text.toString();
    }

    // Helper method pour convertir le byte lue en int (0 ou 1 dans notre cas)
    // Code ASCII du 0 = 48, 1 = 49, etc.
    private int parseInt(int toParse) {
        if (toParse == 48)
            return 0;
        else
            return 1;
    }

    /**
     * Écris le {@code String} spécifié dans le fichier spécifié dans le répertoire
     * courant de l'application.
     * 
     * @param text
     *            le texte à enregistré
     * @param fileName
     *            le nom à donné au fichier enregistré
     * */
    public void writeFile(String text, String fileName) {

        System.out.println("Enregistrement du fichier compressé en cours...");
        PrintWriter out = null;
        try {
            out = new PrintWriter(fileName);
            out.print(text);
            System.out.println("Enregistrement du fichier réussis");
        } catch (FileNotFoundException e) {
            System.out.println("L'Enregistrement du fichier a échoué");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
